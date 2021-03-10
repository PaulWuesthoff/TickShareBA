package com.tickshareba.persistence;


import androidx.test.core.app.ApplicationProvider;

import com.tickshareba.models.UserModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(RobolectricTestRunner.class)
public class UserPersistenceManagerDBHelperTest {
    private IUserPersistenceManager persistenceManager;
    private UserModel userModel;

    @Before
    public void setUp() {
        persistenceManager = new UserPersistenceManagerDBHelper(ApplicationProvider.getApplicationContext());
        userModel = new UserModel("Paul", "Tester", "Testregion", "Example@test.com", "password");
    }

    @After
    public void tearDown() {
        persistenceManager.wipeDatabase();
    }

    @Test
    public void persistUser() {
        boolean persistet = persistenceManager.persistUser(userModel);
        assertThat(persistet, equalTo(true));
    }

    @Test
    public void getUser() {
        persistenceManager.persistUser(userModel);
        assertThat(userModel.getEmailAddress(), equalTo(persistenceManager.getUser(userModel.getEmailAddress()).getEmailAddress()));
    }
}