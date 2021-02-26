package com.tickshareba.management;

import com.tickshareba.Constants;
import com.tickshareba.authentication.Validator;
import com.tickshareba.models.UserModel;
import com.tickshareba.persistence.UserPersistenceManagerDBHelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class UserManagerImpl implements IUserManager {

    private static final Logger LOG = LogManager.getLogger(UserManagerImpl.class);


    @Getter
    private List<UserModel> userList;
    @Getter
    private Map<String, String> errorMap;

    public UserManagerImpl() {
        this.userList = new ArrayList<>();
        this.errorMap = new HashMap<>();
    }

    @Override
    public boolean createUser(String name, String lastName, String region, String emailAddress, String password) {
        boolean success = false;

        if (checkUserData(name, lastName, region, emailAddress, password)) {
            UserModel userModel = new UserModel(formatToUpperCase(name), formatToUpperCase(lastName), region, emailAddress, password);
            userList.add(userModel);
            success = true;
        }
        return success;
    }

    @Override
    public UserModel getUserFromEmail(String emailAddress) {
        return userList.stream().filter(user -> user.getEmailAddress().equals(emailAddress)).findAny().orElse(null);
    }

    private String formatToUpperCase(String toUpperCase) {
        String nameToUpperCase = Character.toUpperCase(toUpperCase.charAt(0)) + toUpperCase.substring(1);
        return nameToUpperCase;
    }

    private boolean checkUserData(String name, String lastName, String region, String emailAddress,
                                  String password) {
        if (name == null || name.isEmpty()) {
            errorMap.put(new Object() {
            }.getClass().getEnclosingMethod().getName(), "Name can not be empty");
            LOG.error("name is null or empty");
            return false;
        }
        if (lastName == null || lastName.isEmpty()) {
            errorMap.put(new Object() {
            }.getClass().getEnclosingMethod().getName(), "Lastname can not be empty");
            LOG.error("lastname is null or empty");
            return false;
        }
        if (region == null || region.isEmpty()) {
            errorMap.put(new Object() {
            }.getClass().getEnclosingMethod().getName(), "Region can not be empty");
            LOG.error("region is null or empty");
            return false;
        }
        if (emailAddress == null || emailAddress.isEmpty() || !Validator.validateEmailAddress(emailAddress)) {
            errorMap.put(new Object() {
            }.getClass().getEnclosingMethod().getName(), "Email can not be empty or does not match pattern: " + Constants.EMAIL_REGEX.getValue());
            LOG.error("email is null or empty or dose not match the pattern");
            return false;
        }
        if (password == null || password.isEmpty() || password.length() < Integer.valueOf(Constants.PASSWORD_LENGTH.getValue())) {
            errorMap.put(new Object() {
            }.getClass().getEnclosingMethod().getName(), "Password can not be empty or is shorter than 7 characters");
            LOG.error("password is null or empty");
            return false;
        }
        return true;
    }
}
