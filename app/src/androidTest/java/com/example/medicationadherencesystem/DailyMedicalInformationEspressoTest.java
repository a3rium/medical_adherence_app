package com.example.medicationadherencesystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class DailyMedicalInformationEspressoTest extends AppCompatActivity {

    @Rule
    public ActivityScenarioRule<DailyMedicalInformation> activityScenarioRule
            = new ActivityScenarioRule<>(DailyMedicalInformation.class);

    @Test
    public void testUsername() {
        onView(withId(R.id.textViewUsername))
            .check(matches(withText("Welcome")));
    }
}
