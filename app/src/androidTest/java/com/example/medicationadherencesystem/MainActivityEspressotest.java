package com.example.medicationadherencesystem;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.mock;

public class MainActivityEspressotest {

    @Mock
    Database dbMock = mock(Database.class);

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Rule
    public IntentsTestRule<MainActivity> intentsTestRule =
            new IntentsTestRule<>(MainActivity.class);

    @Test
        public void testUsernameCreate() {
            onView(withId(R.id.editTextUsername))
                    .perform(replaceText(""))
                    .perform(click());

            onView(withId(R.id.editTextPassword))
                    .perform(replaceText("Password1"));

            onView(withId(R.id.btnCreateAccount))
                    .perform(click());

            onView(withId(R.id.textViewOutput))
                    .check(matches(withText("Error: please enter a username")));
    }

    @Test
        public void testPasswordCreate() {
            onView(withId(R.id.editTextUsername))
                    .perform(replaceText("username"))
                    .perform(click());

            onView(withId(R.id.editTextPassword))
                    .perform(replaceText(""))
                    .perform(click());

            onView(withId(R.id.btnCreateAccount))
                    .perform(click());

            onView(withId(R.id.textViewOutput))
                    .check(matches(withText("Error: please enter a valid password")));
    }

    @Test
        public void testValidInputCreate(){
            onView(withId(R.id.editTextUsername))
                    .perform(replaceText("user"))
                    .perform(click());

            onView(withId(R.id.editTextPassword))
                    .perform(replaceText("passWord123"))
                    .perform(click());

            onView(withId(R.id.btnCreateAccount))
                    .perform(click());

            onView(withId(R.id.textViewOutput))
                    .check(matches(withText("Creating Account")));
    }

    @Test
        public void testCorrectLogin(){

            onView(withId(R.id.editTextPassword))
                    .perform(replaceText("Password1"))
                    .perform(click());

            onView(withId(R.id.editTextUsername))
                    .perform(replaceText("Username"))
                    .perform(click());

            onView(withId(R.id.btnLogin))
                    .perform(click());

            onView(withId(R.id.textViewOutput))
                    .check(matches(withText("Logging in")));
    }

    @Test
        public void testIncorrectLogin(){

            onView(withId(R.id.editTextPassword))
                    .perform(replaceText("incorrect"))
                    .perform(click());

            onView(withId(R.id.editTextUsername))
                    .perform(replaceText("wrong"))
                    .perform(click());

            onView(withId(R.id.btnLogin))
                    .perform(click());

            onView(withId(R.id.textViewOutput))
                    .check(matches(withText("Error: account does not exist")));
    }
}