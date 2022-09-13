package com.example.medicationadherencesystem;

import static java.lang.Character.isDigit;
import static java.lang.Character.isUpperCase;

public class Validator {

    private String input;

    public Validator(String in){
        input = in;
    }

    public boolean validUsername(){
        return input.length()!=0;
    }

    public boolean validPassword(){

        boolean capitalFlag=false, numberFlag=false;
        char ch;

        for(int i=0; i<input.length(); i++){
            ch = input.charAt(i);
            if(isDigit(ch))
                numberFlag = true;
            else if(isUpperCase(ch))
                capitalFlag = true;

            if(capitalFlag && numberFlag)
                return true;
        }
        return false;
    }
}