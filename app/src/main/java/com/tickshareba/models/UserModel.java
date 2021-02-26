package com.tickshareba.models;

import androidx.annotation.NonNull;

import com.tickshareba.authentication.PasswordUtils;
import com.tickshareba.authentication.TokenGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//TODO: would be cool to store let and lang of user here !

@AllArgsConstructor
@Getter
@Setter
public class UserModel {
    private String name;
    private String lastName;
    private String region;
    private String emailAddress;
    private String password;
    private String token;
    private String salt;

    public UserModel(String name, String lastName, String region, String emailAddress, String password) {
        this.name = name;
        this.lastName = lastName;
        this.region = region;
        this.emailAddress = emailAddress;
        this.salt = PasswordUtils.getSalt(30);
        this.password = hashPassword(password);
        this.token = generateToken();


    }

    /**
     * Konstruktor für die Test methoden, da die PasswordUtils von der Andorid Version abhängig sind und wir java testen und nicht Android
     * @param name
     * @param lastName
     * @param region
     * @param emailAddress
     * @param password
     * @param isTest
     */
    public UserModel(String name, String lastName, String region, String emailAddress, String password, boolean isTest) {
        this.name = name;
        this.lastName = lastName;
        this.region = region;
        this.emailAddress = emailAddress;
        this.salt = PasswordUtils.getSalt(30);
        this.password = hashPassword(password, isTest);
        this.token = generateToken();


    }

    private String generateToken() {
        TokenGenerator tokenGenerator = new TokenGenerator(20);
        return tokenGenerator.nextString();
    }

    private String hashPassword(String password) {
        String passwordToHash = PasswordUtils.generateSecurePassword(password, salt);
        return passwordToHash;
    }

    private String hashPassword(String password, boolean isTest) {
        String passwordToHash = PasswordUtils.generateSecurePassword(password, salt, isTest);
        return passwordToHash;
    }

    @NonNull
    @Override
    public String toString() {
        return "Name: " + name + ", Lastname: " + lastName
                + ", Region: " + region + ", Email Address: " + emailAddress + ", Password hash: " + password + ", Token: " + token + ", Salt: " + salt;
    }
}
