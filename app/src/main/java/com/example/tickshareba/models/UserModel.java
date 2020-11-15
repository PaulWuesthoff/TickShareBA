package com.example.tickshareba.models;

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
}
