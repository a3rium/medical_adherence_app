package com.example.medicationadherencesystem;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DailyMedicalInformation extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_medical_information);

        TextView dateView = findViewById(R.id.date);
        setDate(dateView);

        final TextView textViewWelcomeMessage = findViewById(R.id.textViewUsername);
        String username = getIntent().getStringExtra("usernameInput");
        textViewWelcomeMessage.setText("Welcome");
    }

    public void setDate(TextView view){
        String str = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());
        view.setText("Date: " + str);
    }
}
