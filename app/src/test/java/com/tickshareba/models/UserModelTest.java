package com.tickshareba.models;

import com.TestConstants;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class UserModelTest {
    private UserModel userModel;

    private String name = "Paula";
    private String lastName = "Testerin";
    private String region = "TestRegion2";
    private String emailAddress = "test2@example.com";
    private String password = "SehrSicher1234";
    private String token = "Token";
    private String salt = "Salt";

    @Before
    public void setUp(){
        userModel = new UserModel(TestConstants.NAME.getValue(), TestConstants.LASTNAME.getValue(),
                TestConstants.REGION.getValue(), TestConstants.EMAIL_ADDRESS.getValue(),
                TestConstants.PASSWORD.getValue(), true);
    }


    @Test
    public void setName() {
        userModel.setName(name);
        assertThat(userModel.getName(), equalTo(name));
    }

    @Test
    public void setLastName() {
        userModel.setLastName(lastName);
        assertThat(userModel.getLastName(), equalTo(lastName));
    }

    @Test
    public void setRegion() {
        userModel.setRegion(region);
        assertThat(userModel.getRegion(), equalTo(region));
    }

    @Test
    public void setEmailAddress() {
        userModel.setEmailAddress(emailAddress);
        assertThat(userModel.getEmailAddress(), equalTo(emailAddress));
    }

    @Test
    public void setPassword() {
        userModel.setPassword(password);
        assertThat(userModel.getPassword(), equalTo(password));
    }

    @Test
    public void setToken() {
        userModel.setToken(token);
        assertThat(userModel.getToken(), equalTo(token));
    }

    @Test
    public void getName() {
        assertThat(userModel.getName(), equalTo(TestConstants.NAME.getValue()));
    }

    @Test
    public void getLastName() {
        assertThat(userModel.getLastName(), equalTo(TestConstants.LASTNAME.getValue()));
    }

    @Test
    public void getRegion() {
        assertThat(userModel.getRegion(), equalTo(TestConstants.REGION.getValue()));
    }

    @Test
    public void getEmailAddress() {
        assertThat(userModel.getEmailAddress(), equalTo(TestConstants.EMAIL_ADDRESS.getValue()));
    }

    @Test
    public void generatedPasswordTest(){
        assertTrue(StringUtils.isNotEmpty(userModel.getPassword()));
    }

    @Test
    public void generatedTokenTest(){
        assertTrue(StringUtils.isNotEmpty(userModel.getToken()));
    }
}