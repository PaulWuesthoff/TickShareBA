package com.tickshareba.authentication;

import com.tickshareba.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private Validator(){

    }

    private static final Pattern VALID_EMAIL_ADDRESS = Pattern.compile(Constants.Email_Regex.getValue(), Pattern.CASE_INSENSITIVE);

    public static boolean validateEmailAddress(String email){
        Matcher matcher = VALID_EMAIL_ADDRESS.matcher(email);
        return matcher.find();
    }
}
