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

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tickshareba.Constants;
import com.tickshareba.R;
import com.tickshareba.models.TripModel;

import net.sharksystem.asap.ASAPEngineFS;
import net.sharksystem.asap.ASAPException;
import net.sharksystem.asap.ASAPMessages;
import net.sharksystem.asap.ASAPStorage;
import net.sharksystem.asap.apps.ASAPMessageReceivedListener;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import static net.sharksystem.asap.android.example.ASAPExampleApplication.ASAP_EXAMPLE_APPNAME;

public class PlanTripActivity extends ASAPTickShareRootActivity {

    private EditText startingLocation, destination, startingTime;
    final Calendar calender = Calendar.getInstance();

    public static List<TripModel> tripList;

    private ASAPMessageReceivedListener receivedListener;
    private List<String> receivedMessages = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantrip);
        initialiseEditTexts();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        MainActivity.createTripPersistenceManager(this);

        tripList = new ArrayList<>();
        cleanUp();

        super.startWifiP2P();
        super.startBluetooth();
        super.startBluetoothDiscovery();
        super.startBluetoothDiscoverable();

        this.receivedListener = new ASAPMessageReceivedListener() {
            @Override
            public void asapMessagesReceived(ASAPMessages asapMessages) {
                Log.d(getLogStart(), "asapMessageReceived");
                PlanTripActivity.this.doHandleReceivedMessages(asapMessages);
            }
        };

        // set listener to get informed about newly arrived messages
        Log.d(getLogStart(), " activating listener");
        this.getASAPApplication().addASAPMessageReceivedListener(
                ASAP_EXAMPLE_APPNAME, // listen to this app
                this.receivedListener);
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

    // handle incoming messages
    private void doHandleReceivedMessages(ASAPMessages asapMessages) {
        Log.d(this.getLogStart(), "going to handle received messages with uri: "
                + asapMessages.getURI());

        // set up output
        StringBuilder sb = new StringBuilder();
        try {
            Iterator<CharSequence> messagesAsCharSequence = asapMessages.getMessagesAsCharSequence();
            //sb.append("new messages:\n");
            while (messagesAsCharSequence.hasNext()) {
                String receivedMessage = messagesAsCharSequence.next().toString();
                this.receivedMessages.add(receivedMessage);
                sb.append(receivedMessage);
            }
//            //sb.append("received messages: \n");
//            for (String msg : this.receivedMessages) {
//                sb.append(msg);
//            }
        } catch (IOException e) {
            Log.e(this.getLogStart(), "problems when handling received messages: "
                    + e.getLocalizedMessage());
            sb.append(e.getLocalizedMessage());
        }
        String all = sb.toString();
        Log.d("Hello", "Revieced: "+all);
        Gson gson = new GsonBuilder().setLenient().create();
        Type listOfClass = new TypeToken<List<TripModel>>() {}.getType();
        tripList = gson.fromJson(all, listOfClass);
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
