package com.example.medicationadherencesystem;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import java.util.Date;


import static org.mockito.Mockito.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RecordTimeActivityTest {
    @Mock
    Database dbMock = mock(Database.class);

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void checkIsSuccessful() {
        Medication medication = new Medication("test", "testing", new Date(), 1, dbMock);
        Medication medSpy = Mockito.spy(medication);
        medSpy.addMedication();
        verify(dbMock, times(1)).addToMedication(medSpy);
        verify(medSpy, times(1)).addMedication();
    }
}