package com.tickshareba.activities;

import android.content.Intent;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import com.tickshareba.R;
import com.tickshareba.management.IUserManager;
import com.tickshareba.management.UserManagerImpl;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.endsWith;
import static org.junit.Assert.assertTrue;

import static com.UiTestConstants.*;

public class LoginActivityTest {

    private static String TOAST_MESSAGE = "Wrong Email or Password! Please try again. ";

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityActivityTestRule = new ActivityTestRule<>(LoginActivity.class, false, false);




    @Before
    public void setUp() {
        Espresso.onView(withId(R.id.buttonCreateAnAccount)).perform(click());
        Espresso.onView(withId(R.id.inputTextCreateAccountName)).perform(typeText(NAME), closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountLastName)).perform(typeText(LAST_NAME), closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountRegion)).perform(typeText(REGION), closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountEmailAddress)).perform(typeText(EMAIL_ADDRESS), closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountPassword)).perform(typeText(PASSWORD), closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountConfirmPassword)).perform(typeText(PASSWORD), closeSoftKeyboard());
        Espresso.onView(withId(R.id.buttonCreateAccountNext)).perform(click());
    }

    @Test
    public void onUserLoginTestSuccess() {
        Espresso.onView(withId(R.id.buttonAlreadyRegistred)).perform(click());
        Espresso.onView(withId(R.id.inputTextEmailAddressLogin)).perform(typeText(EMAIL_ADDRESS), closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextPasswordLogin)).perform(typeText(PASSWORD), closeSoftKeyboard());
        Espresso.onView(withId(R.id.buttonLoginPageLogin)).perform(click());
        assertTrue(loginActivityActivityTestRule.getActivity() == null);

    }

    @Test
    public void onUserLoginTestShowErrorToast() {
        Espresso.onView(withId(R.id.buttonAlreadyRegistred)).perform(click());
        Espresso.onView(withId(R.id.inputTextEmailAddressLogin)).perform(typeText(EMAIL_ADDRESS), closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextPasswordLogin)).perform(typeText("Password"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.buttonLoginPageLogin)).perform(click());
        Espresso.onView(withText(endsWith(TOAST_MESSAGE))).check(matches(isDisplayed()));

    }
}