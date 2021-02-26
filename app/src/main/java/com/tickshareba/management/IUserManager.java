package com.tickshareba.management;

import com.tickshareba.models.UserModel;

import java.util.List;
import java.util.Map;

public interface IUserManager {
    /**
     * central Method for creating an new User
     *
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
     *
     * @param emailAddress
     * @return
     */
    public UserModel getUserFromEmail(String emailAddress);

    /**
     * @return
     */
    public Map<String, String> getErrorMap();

    /**
     * @return
     */
    public List<UserModel> getUserList();


}
