package com.tickshareba.management;

import com.tickshareba.models.UserModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class UserManagerImpl implements IUserManager {

    private static final Logger LOG = LogManager.getLogger(UserManagerImpl.class);

    List<UserModel> userList = new ArrayList<>();

    @Override
    public boolean createUser(String name, String lastName, String region, String emailAddress, String password) {
       return userList.add(new UserModel(name, lastName, region, emailAddress, password, "token", "1234"));
    }

    @Override
    public UserModel getUserFromEmail(String emailAddress) {
        return userList.get(0);
    }

    @Override
    public boolean persistUser(UserModel user) {
        return false;
    }

    @Override
    public boolean deleteUser(UserModel user) {
        return false;
    }
}
