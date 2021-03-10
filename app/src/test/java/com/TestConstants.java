package com;

import com.tickshareba.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TestConstants {
    NAME("Paul"),
    LASTNAME("Tester"),
    REGION("TestRegion"),
    EMAIL_ADDRESS("test@example.com"),
    PASSWORD("Sicher1234!"),
    TOKEN("TestToken"),
    STARTINGLOCATION("TestLocation"),
    DESTINATION("TestDestination"),
    SEATS_LEFT("3"),
    APP_NAME("TickshareBA"),
    ALICE("Alice"),
    BOB("Bob"),
    URI("asap://exampleURI"),

    ;


    @Getter
    private String value;

}