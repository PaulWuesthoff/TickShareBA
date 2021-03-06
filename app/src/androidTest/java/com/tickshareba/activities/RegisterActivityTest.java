package com.tickshareba.activities;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import com.tickshareba.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.UiTestConstants.EMAIL_ADDRESS;
import static com.UiTestConstants.LAST_NAME;
import static com.UiTestConstants.NAME;
import static com.UiTestConstants.PASSWORD;
import static com.UiTestConstants.REGION;
import static org.junit.Assert.assertTrue;

public class RegisterActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public ActivityTestRule<RegisterActivity> registerActivityActivityTestRule = new ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void createAccountUseCase(){
        Espresso.onView(withId(R.id.buttonCreateAnAccount)).perform(click());
        Espresso.onView(withId(R.id.inputTextCreateAccountName)).perform(typeText(NAME), closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountLastName)).perform(typeText(LAST_NAME), closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountRegion)).perform(typeText(REGION), closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountEmailAddress)).perform(typeText(EMAIL_ADDRESS), closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountPassword)).perform(typeText(PASSWORD), closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountConfirmPassword)).perform(typeText(PASSWORD), closeSoftKeyboard());
        Espresso.onView(withId(R.id.buttonCreateAccountNext)).perform(click());
        assertTrue(registerActivityActivityTestRule.getActivity().isFinishing());
    }

    @Test
    public void createAccountErrorWrongInput(){
        Espresso.onView(withId(R.id.buttonCreateAnAccount)).perform(click());
        Espresso.onView(withId(R.id.inputTextCreateAccountName)).perform(typeText(""), closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountLastName)).perform(typeText(LAST_NAME), closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountRegion)).perform(typeText(REGION), closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountEmailAddress)).perform(typeText(EMAIL_ADDRESS), closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountPassword)).perform(typeText(PASSWORD), closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountConfirmPassword)).perform(typeText(PASSWORD), closeSoftKeyboard());
        Espresso.onView(withId(R.id.buttonCreateAccountNext)).perform(click());
        assertTrue(registerActivityActivityTestRule.getActivity().getAlertDialog().isShowing());
    }
}
