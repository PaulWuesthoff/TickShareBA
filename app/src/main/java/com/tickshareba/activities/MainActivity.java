package com.tickshareba.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tickshareba.management.TripManagerImpl;
import com.tickshareba.management.UserManagerImpl;
import com.tickshareba.persistence.ITripPersistenceManager;
import com.tickshareba.persistence.IUserPersistenceManager;
import com.tickshareba.persistence.TripPersistenceManagerDBHelper;
import com.tickshareba.persistence.UserPersistenceManagerDBHelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;


public class MainActivity extends AppCompatActivity {

    public static Map<String, String> errorMap = new HashMap();

    public static UserManagerImpl userManager;
    public static TripManagerImpl tripManager;
    public static IUserPersistenceManager userPersistenceManagerDBHelper;
    public static ITripPersistenceManager tripPersistenceManager;

    protected static final Logger LOG = LogManager.getLogger(MainActivity.class);

    public static Intent showMainMenu;

    @Getter
    @Setter
    private static UserState userState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userManager = new UserManagerImpl();
        tripManager = new TripManagerImpl();

        showMainMenu = new Intent(this, MainMenuActivity.class);
        startActivity(showMainMenu);
        setUserState(UserState.LOGGED_OUT);
    }

    public static void showSuccessAlert(Context context, String succesAlert) {
        Toast.makeText(context, succesAlert, Toast.LENGTH_LONG).show();
    }

    public static void createPersistenceManager(Context context) {
        userPersistenceManagerDBHelper = new UserPersistenceManagerDBHelper(context);
    }

    public static void createTripPersistenceManager(Context context) {
        tripPersistenceManager = new TripPersistenceManagerDBHelper(context);
    }

    public void onBackClick(View view) {
        startActivity(MainActivity.showMainMenu);
        finish();
    }

}
