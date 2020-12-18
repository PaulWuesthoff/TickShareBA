package com.tickshareba;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Constants {
    Email_Regex("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$"),
    INPUT_REGEX("^[A-Za-z, ]++$"),
    Passwordlength("7"),
    Date_Format("MMM dd,yyy HH:mm"),
    AccountSuccess("Your account has been succsessfully registerd"),
    Trip_Success("Your trip has been succsessfully created"),
    ;

    @Getter
    private String value;

}
