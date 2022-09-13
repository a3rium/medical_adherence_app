package com.example.medicationadherencesystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private String username;
    private String password;
    Button createAccount;
    Button login;
    EditText editTextUsername;
    EditText editTextPassword;
    TextView textViewOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createAccount = findViewById(R.id.btnCreateAccount);
        login = findViewById(R.id.btnLogin);

        editTextPassword = findViewById(R.id.editTextPassword);
        editTextUsername = findViewById(R.id.editTextUsername);
    }

    public void onCreateAccount(View view) {

        username = editTextUsername.getText().toString();
        password = editTextPassword.getText().toString();

        textViewOutput = findViewById(R.id.textViewOutput);

        Validator usernameValidator = new Validator(username);
        Validator passwordValidator = new Validator(password);

        if (!usernameValidator.validUsername())
            textViewOutput.setText("Error: please enter a username");

        if (!passwordValidator.validPassword())
            textViewOutput.setText("Error: please enter a valid password");

        if (usernameValidator.validUsername() && passwordValidator.validPassword()) {
            textViewOutput.setText("Creating Account");

            User user = new User(username, password);

            Database data = new Database();
            data.addUser(user);

            Intent intent = new Intent(getBaseContext(), DailyMedicalInformation.class);
            intent.putExtra("usernameInput", username);
            startActivity(intent);
        }
    }

    public void onLogin(View view) {

        textViewOutput = findViewById(R.id.textViewOutput);
        textViewOutput.setText("");

        username = editTextUsername.getText().toString();
        password = editTextPassword.getText().toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                if (document.getString("password").equals(password) &&
                                        document.getString("username").equals(username)) {

                                    textViewOutput.setText("Logging in");
                                    Intent intent = new Intent(getBaseContext(), DailyMedicalInformation.class);
                                    intent.putExtra("usernameInput", username);
                                    startActivity(intent);
                                    return;

                                } else {
                                    textViewOutput.setText("Error: account does not exist");
                                }
                            }
                        } else
                            Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });
            }
}
