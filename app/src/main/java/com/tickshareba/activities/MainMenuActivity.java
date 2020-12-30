package com.tickshareba.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tickshareba.R;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        try {
            startActivity(intent);
        } catch (Exception e) {
            MainActivity.LOG.error("oops something went wrong: ", e);
        }
    }

    public void onLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        try {
            startActivity(intent);
        } catch (Exception e) {
            MainActivity.LOG.error("oops something went wrong: ", e);
        }
    }

    public void onPlanTrip(View view) {

        ASAPExampleApplication.initializeASAPExampleApplication(this);


        Intent intent = new Intent(this, PlanTripActivity.class);
        try {
            startActivity(intent);
        } catch (Exception e) {
            MainActivity.LOG.error("oops something went wrong: ", e);
        }
    }

    public void onOfferTrip(View view) {
        Intent intent = new Intent(this, OfferTripActivity.class);
        try {
            startActivity(intent);
        } catch (Exception e) {
            MainActivity.LOG.error("oops something went wrong: ", e);
        }
    }
}
