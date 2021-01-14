package com.tickshareba.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.google.gson.Gson;
import com.tickshareba.Constants;
import com.tickshareba.R;
import com.tickshareba.models.UserModel;

import net.sharksystem.asap.ASAPEngineFS;
import net.sharksystem.asap.ASAPException;
import net.sharksystem.asap.ASAPStorage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static net.sharksystem.asap.android.example.ASAPExampleApplication.ASAP_EXAMPLE_APPNAME;

public class OfferTripActivity extends ASAPTickShareRootActivity {

    private EditText startingLocation, destination, startingTime, seatsLeft;

    private AlertDialog dialog;

    final Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offertrip);
        setUpTextFields();
        MainActivity.createTripPersistenceManager(this);

//        MainActivity.tripPersistenceManager.deleteOldTrips();

        cleanUp();

        super.startWifiP2P();
        super.startBluetooth();
        super.startBluetoothDiscovery();
        super.startBluetoothDiscoverable();
    }

    public void onOfferNow(View view) {
        UserModel userModel = null;
        if (MainActivity.getUserState().equals(UserState.LOGGED_IN)) {
            userModel = MainActivity.userManager.getUserList().get(0);
        }

        String locationString = startingLocation.getText().toString().trim();
        String destinationString = destination.getText().toString().trim();
        String startingTimeString = startingTime.getText().toString().trim();
        String seatsLeftString = seatsLeft.getText().toString().trim();

        if (MainActivity.getUserState().equals(UserState.LOGGED_IN)) {
            if (!checkUserModel(userModel)) {
                showErrorAlert();
                finish();
            }
            if (MainActivity.tripManager.createTrip(null, locationString, destinationString, startingTimeString, seatsLeftString, userModel.getToken())) {
                MainActivity.showSuccessAlert(this, Constants.TRIP_SUCCESS.getValue());
                System.out.println(MainActivity.tripPersistenceManager.persistTrip(MainActivity.tripManager.getTripList().get(0)) + "##########");
                MainActivity.tripManager.getTripList().clear();
                System.out.println(MainActivity.tripPersistenceManager.getTrip(userModel.getToken()).toString());

                Gson gson = new Gson();
                String tripsToString = gson.toJson(MainActivity.tripPersistenceManager.getAllTrips());
                byte[] byteContent = tripsToString.getBytes();

                Log.d(this.getLogStart(), "Sending the follwoing: " + tripsToString.toString());
                Log.d(this.getLogStart(), "going to send messageBytes: " + byteContent.toString());

                try {
                    this.sendASAPMessage(
                            ASAP_EXAMPLE_APPNAME,
                            Constants.URI.getValue(),
                            byteContent,
                            true);
                } catch (ASAPException e) {
                    Log.e(this.getLogStart(), "when sending asap message: " + e.getLocalizedMessage());
                }
            } else {
                showErrorAlert();
            }
        } else if (MainActivity.getUserState().equals(UserState.LOGGED_OUT)) {
            if (MainActivity.tripManager.createTripWithouUserToken(locationString, destinationString, startingTimeString, seatsLeftString)) {
                MainActivity.showSuccessAlert(this, Constants.TRIP_SUCCESS.getValue());
                System.out.println(MainActivity.tripPersistenceManager.persistTrip(MainActivity.tripManager.getTripList().get(0)) + "##########");

               // MainActivity.tripPersistenceManager.deleteOldTrips();
                Gson gson = new Gson();
                String tripsToString = gson.toJson(MainActivity.tripPersistenceManager.getAllTrips());
                byte[] byteContent = tripsToString.getBytes();

                Log.d(this.getLogStart(), "Sending the follwoing: " + tripsToString.toString());
                Log.d(this.getLogStart(), "going to send messageBytes: " + byteContent.toString());

                try {
                    this.sendASAPMessage(
                            ASAP_EXAMPLE_APPNAME,
                            Constants.URI.getValue(),
                            byteContent,
                            true);
                } catch (ASAPException e) {
                    Log.e(this.getLogStart(), "when sending asap message: " + e.getLocalizedMessage());
                }
                MainActivity.tripManager.getTripList().clear();
            } else {
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
        new DatePickerDialog(OfferTripActivity.this, datePickerDialog,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void onTimeClick(View view) {
        new TimePickerDialog(OfferTripActivity.this,
                timeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), true).show();
    }

    TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            updateLabelDate();
        }
    };

    DatePickerDialog.OnDateSetListener datePickerDialog = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabelDate();
        }
    };


    private void updateLabelDate() {
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

    private boolean checkUserModel(UserModel userModel) {
        return userModel != null && !userModel.getToken().equals("") && !userModel.getToken().equals(null);
    }

    ASAPStorage asapStorage;

    private void cleanUp() {
        try {
            this.setupCleanASAPStorage();
        } catch (IOException | ASAPException e) {
            Log.d(this.getLogStart(), "exception: " + e.getLocalizedMessage());
        } catch (RuntimeException e) {
            Log.d(this.getLogStart(), "runtime exception: " + e.getLocalizedMessage());
        }
    }

    private void setupCleanASAPStorage() throws IOException, ASAPException {
        String absoluteFolderName = this.getASAPApplication().getApplicationRootFolder(ASAP_EXAMPLE_APPNAME);
        Log.d(this.getLogStart(), "going to clean folder:  " + absoluteFolderName);

        ASAPEngineFS.removeFolder(absoluteFolderName);

        Log.d(this.getLogStart(), "create asap storage with:  "
                + this.getASAPApplication().getOwnerID()
                + " | "
                + this.getASAPApplication().getApplicationRootFolder(ASAP_EXAMPLE_APPNAME)
                + " | "
                + ASAP_EXAMPLE_APPNAME
        );

        this.asapStorage = ASAPEngineFS.getASAPStorage(
                this.getASAPApplication().getOwnerID().toString(),
                this.getASAPApplication().getApplicationRootFolder(ASAP_EXAMPLE_APPNAME),
                ASAP_EXAMPLE_APPNAME);
    }

}
