package com.example.medicationadherencesystem;

import org.junit.Test;
import static org.junit.Assert.*;


import java.util.Calendar;
import java.util.Date;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MedicationTest {
    @Test
    public void checkFutureTime() {
        Calendar futureCal = Calendar.getInstance();
        futureCal.set(Calendar.HOUR_OF_DAY, futureCal.get(Calendar.HOUR_OF_DAY) + 2);
        Date futureTime = futureCal.getTime();

        Medication testMed = new Medication();
        assertFalse("Future time was not handled correctly", testMed.checkValidTime(futureTime));
    }

    @Test
    public void checkCurrentTime() {
        Calendar cal = Calendar.getInstance();
        Date dateNow = cal.getTime();

        Medication testMed = new Medication();
        assertTrue("Current time was handled correctly", testMed.checkValidTime((dateNow)));

    }

    @Test
    public void checkPastTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 2);
        Date pastDate = cal.getTime();

        Medication testMed = new Medication();
        assertFalse("Past time was handled correctly (24hrs before)", testMed.checkValidTime(pastDate));
    }

}