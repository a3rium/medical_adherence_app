package com.example.medicationadherencesystem;

import org.junit.Test;
import static org.junit.Assert.*;

public class ValidatorTest {

    @Test
    public void validUsernameTest(){
        Validator v1 = new Validator("");
        Validator v2 = new Validator("joe");

        assertFalse(v1.validUsername());
        assertTrue(v2.validUsername());
    }

    @Test
    public void validPasswordTest(){
        Validator v1 = new Validator("");
        Validator v2 = new Validator("password");
        Validator v3 = new Validator("Password");
        Validator v4 = new Validator("password1");
        Validator v5 = new Validator("Password1");
        Validator v6 = new Validator("pasS1word");

        assertFalse(v1.validPassword());
        assertFalse(v2.validPassword());
        assertFalse(v3.validPassword());
        assertFalse(v4.validPassword());
        assertTrue(v5.validPassword());
        assertTrue(v6.validPassword());
    }
}
