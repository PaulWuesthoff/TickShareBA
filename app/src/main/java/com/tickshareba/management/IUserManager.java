package com.tickshareba.management;

import com.tickshareba.models.UserModel;

public interface IUserManager {
    /**
     *  central Method for creating an new User
     * @param name
     * @param lastName
     * @param region
     * @param emailAddress
     * @param password
     * @return
     */
    public boolean createUser(String name, String lastName, String region, String emailAddress, String password);

    /**
     * searches an user with the provided EmailAddress
     * @param emailAddress
     * @return
     */
    public UserModel getUserFromEmail(String emailAddress);


}
