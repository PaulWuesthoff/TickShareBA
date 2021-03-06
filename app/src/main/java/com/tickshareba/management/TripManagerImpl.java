package com.tickshareba.management;

import com.tickshareba.Constants;
import com.tickshareba.models.TripModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class TripManagerImpl implements ITripManager {

    private static final Logger LOG = LogManager.getLogger(TripManagerImpl.class);

    @Getter
    @Setter
    private List<TripModel> tripList = new ArrayList<>();

    @Override
    public boolean createTrip(String ID, String startingLocation, String destination, String startingTime, String seatsLeft, String userToken) {
        if (checkTripValuesWithToken(ID, startingLocation, destination, startingTime, seatsLeft, userToken)) {
            return tripList.add(new TripModel(null, startingLocation, destination, startingTime, seatsLeft, userToken));
        }
        LOG.error("Error while creating trip with values: ID: " + ID + ", startinglocation: " + startingLocation + ", destination: " + destination);
        return false;
    }

    @Override
    public boolean createTripWithoutUserToken(String startingLocation, String destination, String startingTime, String seatsLeft) {
        if (checkTripValuesWithoutToken(startingLocation, destination, startingTime, seatsLeft)) {
            return tripList.add(new TripModel(startingLocation, destination, startingTime, seatsLeft));
        }
        LOG.error("Error while creating trip with values: startinglocation: " + startingLocation + ", destination: " + destination);
        return false;
    }

    private boolean checkTripValuesWithToken(String ID, String startingLocation, String destination, String startingTime, String seatsLeft, String userToken) {
        Boolean returnVal = true;
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT.getValue());
        if (startingTime == null || startingTime.isEmpty()) {
            LOG.error("Time is empty or null");
            returnVal = false;
        } else {
            try {
                date = simpleDateFormat.parse(startingTime);
            } catch (ParseException e) {
                LOG.error("Error parsing time", e);
            }
        }
        if (startingLocation == null || startingLocation.isEmpty()) {
            LOG.error("starting Location is empty or null");
            returnVal = false;
        }
        if (destination == null || destination.isEmpty()) {
            LOG.error("starting Location is empty or null");
            returnVal = false;
        }
        if (date == null || startingTime.isEmpty() || date.before(getYesterdaysDate())) {
            LOG.error("Date is null or in the past");
            returnVal = false;
        }
        if (seatsLeft == null || seatsLeft.isEmpty() || Integer.parseInt(seatsLeft) < 0) {
            LOG.error("Seats left is null or smaller then zero");
            returnVal = false;
        }
        if (userToken == null || userToken.isEmpty()) {
            LOG.error("User token is empty");
            returnVal = false;
        }
        return returnVal;
    }

    private boolean checkTripValuesWithoutToken(String startingLocation, String destination, String startingTime, String seatsLeft) {
        Boolean returnVal = true;
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT.getValue());
        if (startingTime == null || startingTime.isEmpty()) {
            LOG.error("Time is empty or null");
            returnVal = false;
        } else {
            try {
                date = simpleDateFormat.parse(startingTime);
            } catch (ParseException e) {
                LOG.error("Error parsing time", e);
            }
        }
        if (startingLocation == null || startingLocation.isEmpty()) {
            LOG.error("starting Location is empty or null");
            returnVal = false;
        }
        if (destination == null || destination.isEmpty()) {
            LOG.error("starting Location is empty or null");
            returnVal = false;
        }
        if (date == null || startingTime.isEmpty() || date.before(getYesterdaysDate())) {
            LOG.error("Date is null or in the past");
            returnVal = false;
        }
        if (seatsLeft == null || seatsLeft.isEmpty() || Integer.parseInt(seatsLeft) < 0) {
            LOG.error("Seats left is null or smaller then zero");
            returnVal = false;
        }
        return returnVal;

    }

    private Date getYesterdaysDate() {
       final Calendar cal = Calendar.getInstance();
       cal.add(Calendar.DATE, -1);
       return cal.getTime();
    }
}
