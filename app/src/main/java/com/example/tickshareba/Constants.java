package com.example.tickshareba;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Constants {
    Email_Regex("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$"),
    Passwordlength("7"),
    Date_Format("MMM dd,yyy HH:mm"),
    AccountSuccess("Your account has been succsessfully registerd"),
    Trip_Success("Your trip has been succsessfully created"),
    ;

    @Getter
    private String value;

}
