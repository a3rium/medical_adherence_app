package com.example.medicationadherencesystem;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RecordTimeActivity extends AppCompatActivity {

    private TextView medicineNameText;
    private TextView medicineDescriptionText;
    private TextView expectedTimeText;
    private TextView errorText;
    private EditText enteredTimeEditText;
    private Button submitTimeButton;
    private Medication medicationItem;
    private Date enteredTime;

    private Database database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set layout for activity
        setContentView(R.layout.record_time);
        medicineNameText = findViewById(R.id.medName);
        medicineDescriptionText = findViewById(R.id.medDescription);
        expectedTimeText = findViewById(R.id.expectedTime);
        errorText = findViewById(R.id.timeErrorText);
        enteredTimeEditText = findViewById(R.id.enteredTime);
        submitTimeButton = findViewById(R.id.submitTimeButton);

        enteredTime = new Date();
        database = new Database();

        FirebaseFirestore.setLoggingEnabled(true);

        initializeData();

        enteredTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OnClick Edit Time", "Clicked!");
                Calendar currentTime = Calendar.getInstance();
                int hours = currentTime.get(Calendar.HOUR_OF_DAY);
                int minutes = currentTime.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog;
                timePickerDialog = new TimePickerDialog(RecordTimeActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        enteredTimeEditText.setText(hourOfDay + ":" + minute);
                        Calendar cal = Calendar.getInstance();
                        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        cal.set(Calendar.MINUTE, minute);
                        enteredTime = cal.getTime();
                    }
                }, hours, minutes, true);
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
            }
        });
    }



    public void initializeData() {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Calendar calendar = Calendar.getInstance();
        enteredTimeEditText.setText(df.format(calendar.getTime()));
        String medName = "name";
        String medDesc = "description";
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        Date date = calendar.getTime();
        int dosage = 5;

        medicationItem = new Medication(medName, medDesc, date , dosage, database);


    }


    public void onTimeSubmit(View view) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        try {
            Date date = df.parse(enteredTimeEditText.getText().toString());
            if(medicationItem.checkValidTime(date)) {
                medicationItem.addMedication();
            }
            else {
                errorText.setText(getResources().getString(R.string.futureTimeErrorText));
            }
        } catch (ParseException e) {
            // do something
        }

    }
}
