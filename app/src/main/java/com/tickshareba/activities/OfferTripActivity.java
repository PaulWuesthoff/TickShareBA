package com.tickshareba.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.tickshareba.Constants;
import com.tickshareba.R;
import com.tickshareba.models.UserModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class OfferTripActivity extends AppCompatActivity {

    private EditText startingLocation, destination, startingTime, seatsLeft;

    private AlertDialog dialog;

    final Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offertrip);
        setUpTextFields();
        MainActivity.createTripPersistenceManager(this);
    }

    public void onOfferNow(View view) {
        UserModel userModel = null;
        if (MainActivity.getUserState().equals(UserState.LOGGED_IN)) {
            userModel = MainActivity.userManager.getUserList().get(0);
        }

        String locationString = startingLocation.getText().toString().trim();
        String destinationString = destination.getText().toString().trim();
        String startingTimString = startingTime.getText().toString().trim();
        String seatsLeftString = seatsLeft.getText().toString().trim();

        if (MainActivity.getUserState().equals(UserState.LOGGED_IN)) {
            if(!checkUserModel(userModel)){
                showErrorAlert();
                finish();
            }
            if (MainActivity.tripManager.createTrip(locationString, destinationString, startingTimString, seatsLeftString, userModel.getToken())) {
                MainActivity.showSuccessAlert(this, Constants.TRIP_SUCCESS.getValue());
                System.out.println(MainActivity.tripPersistenceManager.persistTrip(MainActivity.tripManager.getTripList().get(0))+"##########");
                MainActivity.tripManager.getTripList().clear();
                System.out.println(MainActivity.tripPersistenceManager.getTrip(userModel.getToken()).toString());
            }else{
                showErrorAlert();
            }
        } else if(MainActivity.getUserState().equals(UserState.LOGGED_OUT)){
            if (MainActivity.tripManager.createTripWithouUserToken(locationString, destinationString, startingTimString, seatsLeftString)) {
                MainActivity.showSuccessAlert(this, Constants.TRIP_SUCCESS.getValue());
                System.out.println(MainActivity.tripPersistenceManager.persistTrip(MainActivity.tripManager.getTripList().get(0))+"##########");
                MainActivity.tripManager.getTripList().clear();
            }else{
                showErrorAlert();
            }
        }
    }

    public void onDateNowClick(View view) {
        long millis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT.getValue());
        Date date = new Date(millis);
        startingTime.setText(simpleDateFormat.format(date));
    }

    public void onDateClick(View view) {
        new DatePickerDialog(OfferTripActivity.this,datePickerDialog,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void onTimeClick(View view){
        new TimePickerDialog(OfferTripActivity.this,
                timeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),true).show();
    }

    TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
            calendar.set(Calendar.MINUTE,minute);
            updateLabelDate();
        }
    };

    DatePickerDialog.OnDateSetListener datePickerDialog = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabelDate();
        }
    };
    private void updateLabelDate(){
        String myFormat = Constants.DATE_FORMAT.getValue();
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.GERMAN);
        startingTime.setText(sdf.format(calendar.getTime()));
    }

    private void setUpTextFields() {
        startingLocation = findViewById(R.id.inputTextWhereDouYouStart);
        destination = findViewById(R.id.inputTextWhereDoYouWantToGo);
        startingTime = findViewById(R.id.inputTextWhenDoYouStart);
        seatsLeft = findViewById(R.id.inputTextHowManySeatsLeft);
    }
    private void showErrorAlert() {
        dialog = new AlertDialog.Builder(this)
                .setTitle("Offer Trip")
                .setMessage("There has been an Error creating your Trip, please try again! ")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    private boolean checkUserModel(UserModel userModel){
        return userModel != null && !userModel.getToken().equals("") && !userModel.getToken().equals(null);
    }

}
