package com.tickshareba.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;


import com.tickshareba.Constants;
import com.tickshareba.R;
import com.tickshareba.models.TripModel;

import net.sharksystem.asap.ASAP;
import net.sharksystem.asap.ASAPException;
import net.sharksystem.asap.ASAPMessageReceivedListener;
import net.sharksystem.asap.android.apps.ASAPActivity;
import net.sharksystem.asap.android.apps.ASAPAndroidPeer;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PlanTripActivity extends ASAPActivity {

    private EditText startingLocation, destination, startingTime;
    final Calendar calender = Calendar.getInstance();

    private ASAPAndroidPeer peer;

    List<String> list;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantrip);
        initialiseEditTexts();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        MainActivity.createTripPersistenceManager(this);

        list = new ArrayList<>();
        peer = super.getASAPAndroidPeer();

        super.startWifiP2P();
        super.startBluetooth();
        super.startBluetoothDiscovery();
        super.startBluetoothDiscoverable();

        ASAPMessageReceivedListener listener = new TickshareASAPMessageReceivedListener();
        peer.addASAPMessageReceivedListener("Tickshare", listener);



    }

    public void onButtonSearchClick(View view) {
        String startingLocation = this.startingLocation.getEditableText().toString().trim();
        String destination = this.destination.getEditableText().toString().trim();

        //TODO: Add startingtime check when searching for trips
        String startingTime = this.startingTime.getEditableText().toString().trim();

        Intent intent = new Intent(this, ShowTripsActivity.class);
        startActivity(intent);
    }

    public void onButtonBackClick(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
        finish();
    }

    public void onDateNowClick(View view) {
        long millis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT.getValue());
        Date date = new Date(millis);
        startingTime.setText(simpleDateFormat.format(date));
    }

    public void onDateClick(View view) {
        new DatePickerDialog(PlanTripActivity.this, datePickerDialog,
                calender.get(Calendar.YEAR), calender.get(Calendar.MONTH), calender.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void onTimeClick(View view) {
        new TimePickerDialog(PlanTripActivity.this,
                timeSetListener,
                calender.get(Calendar.HOUR_OF_DAY),
                calender.get(Calendar.MINUTE), true).show();
    }

    TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            calender.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calender.set(Calendar.MINUTE, minute);
            updateLabelDate();
        }
    };

    DatePickerDialog.OnDateSetListener datePickerDialog = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calender.set(Calendar.YEAR, year);
            calender.set(Calendar.MONTH, month);
            calender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabelDate();
        }
    };

    private void updateLabelDate() {
        String myFormat = Constants.DATE_FORMAT.getValue();
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        startingTime.setText(sdf.format(calender.getTime()));
    }

    private void initialiseEditTexts() {
        startingLocation = findViewById(R.id.editTextWhereDoYouWantToStart);
        destination = findViewById(R.id.editTextWhereToGo);
        startingTime = findViewById(R.id.editTextWhenDoYouWantToGo);

    }

}
