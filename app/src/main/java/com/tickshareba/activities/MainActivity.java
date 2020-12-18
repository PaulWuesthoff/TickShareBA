package com.tickshareba.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tickshareba.management.TripManagerImpl;
import com.tickshareba.management.UserManagerImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    public static Map<String, String> errorMap = new HashMap();

    public static UserManagerImpl userManager;
    public static TripManagerImpl tripManager;

    protected static final Logger LOG = LogManager.getLogger(MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userManager = new UserManagerImpl();
        tripManager = new TripManagerImpl();

        Intent showMainMenu = new Intent(this, MainMenuActivity.class);
        startActivity(showMainMenu);
    }

    public static void showSuccessAlert(Context context, String succesAlert) {
        Toast.makeText(context, succesAlert, Toast.LENGTH_LONG).show();
    }

}
