package com.tickshareba;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Constants {
    EMAIL_REGEX("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$"),
    INPUT_REGEX("^[A-Za-z, ]++$"),
    DATE_FORMAT("MMM dd,yyy HH:mm"),
    AccountSuccess("Your account has been succsessfully registerd"),
    TRIP_SUCCESS("Your trip has been succsessfully created"),
    PASSWORD_LENGTH("8"),
    LOGIN_SUCCESS("Logged in"),
    URI("asap://exampleURI"),
    ;

    @Getter
    private String value;

}
