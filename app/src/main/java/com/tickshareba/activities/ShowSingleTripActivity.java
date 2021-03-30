package com.tickshareba.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tickshareba.R;
import com.tickshareba.models.TripModel;

import net.sharksystem.asap.ASAPException;

public class ShowSingleTripActivity extends AppCompatActivity {

    TripModel trip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showsingletrip);
        fillTextView();
    }

    private void fillTextView() {
        TextView textView = findViewById(R.id.textViewShowSingleTripInformation);
        trip = MainActivity.tripManager.getTripList().get(ShowTripsActivity.position);
        textView.setText(trip.toString());
    }

    public void onButtonBackClick(View view) {
        Intent intent = new Intent(this, ShowTripsActivity.class);
        startActivity(intent);
        finish();
    }

    public void onButtonAcceptClick(View view) {
        try {
            PlanTripActivity.sendAcknowlagement(trip.getId(), trip.getUserToken() == null ? "unkown user" : trip.getUserToken());
        } catch (ASAPException e) {
            e.printStackTrace();
        }
        finish();
    }

}
