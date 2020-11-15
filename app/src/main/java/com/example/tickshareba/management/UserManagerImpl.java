package com.example.tickshareba.management;

import com.example.tickshareba.models.UserModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserManagerImpl implements IUserManager {

    private static final Logger LOG = LogManager.getLogger(UserManagerImpl.class);

    @Override
    public boolean createUser(String name, String lastName, String region, String emailAddress, String password) {
        return false;
    }

    @Override
    public UserModel getUserFromEmail(String emailAddress) {
        return null;
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
