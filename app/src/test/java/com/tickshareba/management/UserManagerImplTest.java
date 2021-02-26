package com.tickshareba.management;

import com.TestConstants;
import com.tickshareba.Constants;
import com.tickshareba.models.UserModel;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class UserManagerImplTest {

    private IUserManager userManager;

    @Before
    public void setUp() {
        userManager = new UserManagerImpl();
    }

    @Test
    public void createUserSuccess() {
        assertTrue(userManager.createUser(TestConstants.NAME.getValue(), TestConstants.LASTNAME.getValue(),
                TestConstants.REGION.getValue(), TestConstants.EMAIL_ADDRESS.getValue(), TestConstants.PASSWORD.getValue()));
    }

    @Test
    public void createUserWithSpecialCharsSuccess() {
        assertTrue(userManager.createUser("ä,ö,á,ü", TestConstants.LASTNAME.getValue(),
                TestConstants.REGION.getValue(), TestConstants.EMAIL_ADDRESS.getValue(), TestConstants.PASSWORD.getValue()));
    }

    @Test
    public void createUserValuesNull() {
        assertFalse(userManager.createUser(null, null, null, null, null));
        assertTrue(userManager.getErrorMap().containsValue("Name can not be empty"));
    }

    @Test
    public void createUservalueNull() {
        assertFalse(userManager.createUser(TestConstants.NAME.getValue(), TestConstants.LASTNAME.getValue(),
                TestConstants.REGION.getValue(), TestConstants.EMAIL_ADDRESS.getValue(), null));
        assertTrue(userManager.getErrorMap().containsValue("Password can not be empty or is shorter than 7 characters"));
    }

    @Test
    public void createUserNonValidEmailAddress() {
        assertFalse(userManager.createUser(TestConstants.NAME.getValue(), TestConstants.LASTNAME.getValue(),
                TestConstants.REGION.getValue(), "email", TestConstants.PASSWORD.getValue()));
        assertTrue(userManager.getErrorMap().containsValue("Email can not be empty or does not match pattern: " + Constants.EMAIL_REGEX.getValue()));
    }

    @Test
    public void createUserPasswordToShort() {
        assertFalse(userManager.createUser(TestConstants.NAME.getValue(), TestConstants.LASTNAME.getValue(),
                TestConstants.REGION.getValue(), TestConstants.EMAIL_ADDRESS.getValue(), "1234"));
        assertTrue(userManager.getErrorMap().containsValue("Password can not be empty or is shorter than 7 characters"));
    }


    @Test
    public void getUserFromEmail() {
        userManager.createUser(TestConstants.NAME.getValue(), TestConstants.LASTNAME.getValue(),
                TestConstants.REGION.getValue(), TestConstants.EMAIL_ADDRESS.getValue(), TestConstants.PASSWORD.getValue());
        assertThat(userManager.getUserList().get(0), equalTo(userManager.getUserFromEmail(TestConstants.EMAIL_ADDRESS.getValue())));
    }

    @Test
    public void getUserList() {
        userManager.createUser(TestConstants.NAME.getValue(), TestConstants.LASTNAME.getValue(),
                TestConstants.REGION.getValue(), TestConstants.EMAIL_ADDRESS.getValue(), TestConstants.PASSWORD.getValue());
        assertNotNull(userManager.getUserList());
    }
}