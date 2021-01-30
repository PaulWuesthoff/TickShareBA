package com.tickshareba.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tickshareba.R;
import com.tickshareba.authentication.TokenGenerator;

import net.sharksystem.asap.ASAP;
import net.sharksystem.asap.ASAPException;
import net.sharksystem.asap.android.apps.ASAPAndroidPeer;
import net.sharksystem.asap.android.example.ExampleAppDefinitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class MainMenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            if(!ASAPAndroidPeer.peerInitialized()) {
                Collection<CharSequence> formats = new ArrayList<>();
                formats.add("Tickshare");
                String peerName;
                if(MainActivity.getUserState() == UserState.LOGGED_IN){
                     peerName = ASAP.createUniqueID();
                }else{
                    peerName = MainActivity.userManager.getUserList().get(0).getToken();
                }

                ASAPAndroidPeer.initializePeer(peerName, formats, this);
            }

            if(!ASAPAndroidPeer.peerStarted()) {
                ASAPAndroidPeer.startPeer();
            }
        } catch (IOException | ASAPException e) {
            e.printStackTrace();
            Toast.makeText(this,
                    "fatal: " + e.getLocalizedMessage(),
                    Toast.LENGTH_LONG).show();
        }
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
