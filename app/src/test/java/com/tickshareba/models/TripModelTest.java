package com.tickshareba.models;

import com.TestConstants;
import com.tickshareba.Constants;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TripModelTest {

    private TripModel tripModel;

    private String startingLocation = "Berlin";
    private String destination = "Hamburg";
    private String startingTime = getCurrentDatePlusOneWeek();
    private String seatsLeft = "2";


    @Before
    public void setUp() {
        tripModel = new TripModel(TestConstants.STARTINGLOCATION.getValue(), TestConstants.DESTINATION.getValue(),
                getCurrentDateMinusOneWeek(), TestConstants.SEATS_LEFT.getValue());
    }

    @Test
    public void setStartingLocation() {
        tripModel.setStartingLocation(startingLocation);
        assertThat(tripModel.getStartingLocation(), equalTo(startingLocation));
    }

    @Test
    public void setDestination() {
        tripModel.setDestination(destination);
        assertThat(tripModel.getDestination(), equalTo(destination));
    }

    @Test
    public void setStartingTime() {
        tripModel.setStartingTime(startingTime);
        assertThat(tripModel.getStartingTime(), equalTo(startingTime));
    }

    @Test
    public void setSeatsLeft() {
        tripModel.setSeatsLeft(seatsLeft);
        assertThat(tripModel.getSeatsLeft(), equalTo(seatsLeft));
    }

    @Test
    public void getStartingLocation() {
        assertThat(tripModel.getStartingLocation(), equalTo(TestConstants.STARTINGLOCATION.getValue()));
    }

    @Test
    public void getDestination() {
        assertThat(tripModel.getDestination(), equalTo(TestConstants.DESTINATION.getValue()));
    }

    @Test
    public void getStartingTime() {
        assertThat(tripModel.getStartingTime(), equalTo(getCurrentDateMinusOneWeek()));
    }

    @Test
    public void getSeatsLeft() {
        assertThat(tripModel.getSeatsLeft(), equalTo(TestConstants.SEATS_LEFT.getValue()));
    }

    private String getCurrentDateMinusOneWeek() {
        long millis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT.getValue());
        Date date = new Date(millis - (7 * millis));
        return simpleDateFormat.format(date);
    }

    private String getCurrentDatePlusOneWeek() {
        long millis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT.getValue());
        Date date = new Date(millis + (7 * millis));
        return simpleDateFormat.format(date);
    }
}