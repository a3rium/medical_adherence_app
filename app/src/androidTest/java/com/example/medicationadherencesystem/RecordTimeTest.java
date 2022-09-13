package com.example.medicationadherencesystem;

import android.util.Log;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Rule;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


public class RecordTimeTest {

    @Rule
    public ActivityScenarioRule<RecordTimeActivity> activityScenarioRule
            = new ActivityScenarioRule<>(RecordTimeActivity.class);

    @Test
    public void checkLayout() {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Calendar futureTime = Calendar.getInstance();

        onView(withId(R.id.medName))
                .check(matches(withText("Name")));

        onView(withId((R.id.medDescription)))
                .check(matches(withText("Description")));

        onView(withId(R.id.expectedTime))
                .check(matches(withText("00:00")));

        onView((withId(R.id.enteredTime)))
                .check(matches(withText(df.format(futureTime.getTime()))));

        onView(withId(R.id.submitTimeButton))
                .check(matches(withText("Submit")));

    }

    @Test
    public void checkFutureTime() {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");

        Calendar futureTime = Calendar.getInstance();
        int hours = futureTime.get(Calendar.HOUR_OF_DAY);
        futureTime.set(Calendar.HOUR_OF_DAY, hours + 2);

        Log.d("RTT/", futureTime.getTime().toString());

        onView(withId(R.id.enteredTime))
                .check(matches(withText(df.format(new Date()))))
                .perform(replaceText(df.format(futureTime.getTime())));

        onView(withId(R.id.submitTimeButton))
                .perform(click());

        onView(withId(R.id.timeErrorText))
                .check(matches(withText(R.string.futureTimeErrorText)));
    }



}

