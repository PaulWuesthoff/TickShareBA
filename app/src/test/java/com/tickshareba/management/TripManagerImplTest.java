package com.tickshareba.management;

import com.TestConstants;
import com.tickshareba.Constants;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class TripManagerImplTest {

    private ITripManager tripManager;
    private Date date;

    @Before
    public void setUp() {
        tripManager = new TripManagerImpl();
    }

    @Test
    public void createTripSuccess() {
        assertTrue(tripManager.createTrip("1", "Teststadt", "Ankunftstadt",
                getCurrentDatePlusOneWeek(), "3", "Token"));
    }

    @Test
    public void createTripALlValuesNull() {
        assertFalse(tripManager.createTrip(null, null, null,
                null, null, null));
    }

    @Test
    public void createTripOneValueNull() {
        assertFalse(tripManager.createTrip("1", "Teststadt", "Ankunftstadt",
                getCurrentDatePlusOneWeek(), null, "Token"));
    }

    @Test
    public void createTripEmptyValue() {
        assertFalse(tripManager.createTrip("1", "", "",
                getCurrentDatePlusOneWeek(), "", ""));
    }

    @Test
    public void createTripSeatsLeftIsZero() {
        assertTrue(tripManager.createTrip("1", "Teststadt", "Ankunftstadt",
                getCurrentDatePlusOneWeek(), "0", "Token"));
    }

    @Test
    public void createTripSeatsLeftIsNegative() {
        assertFalse(tripManager.createTrip("1", "Teststadt", "Ankunftstadt",
                getCurrentDatePlusOneWeek(), "-1", "Token"));
    }

    @Test
    public void createTripStartingTimeIsInPast() {
        assertFalse(tripManager.createTrip("1", "Teststadt", "Ankunftstadt",
                getCurrentDateMinusOneWeek(), "3", "Token"));
    }

    @Test
    public void createTripWithoutUserTokenAllValuesNull() {
        assertFalse(tripManager.createTripWithoutUserToken(null, null, null, null));
    }

    @Test
    public void createTripWithoutUserTokenOneValueNull() {
        assertFalse(tripManager.createTripWithoutUserToken("Teststadt", "Ankunftstadt", getCurrentDatePlusOneWeek(), null));
    }

    @Test
    public void createTripWithoutUserTokenEmptyValue() {
        assertFalse(tripManager.createTripWithoutUserToken("", "Ankunftstadt", getCurrentDatePlusOneWeek(), "3"));
    }

    @Test
    public void createTripWithoutUserTokenSeatsLeftIsZero() {
        assertTrue(tripManager.createTripWithoutUserToken("Teststadt", "Ankunftstadt", getCurrentDatePlusOneWeek(), "0"));
    }

    @Test
    public void createTripWithoutUserTokenSeatsLeftIsNegative() {
        assertFalse(tripManager.createTripWithoutUserToken("Teststadt", "Ankunftstadt", getCurrentDatePlusOneWeek(), "-3"));
    }

    @Test
    public void createTripWithoutUserTokenStartingTimeIsInPast() {
        assertFalse(tripManager.createTripWithoutUserToken("Teststadt", "Ankunftstadt", getCurrentDateMinusOneWeek(), "3"));
    }

    @Test
    public void getTripList() {
        tripManager.createTrip("1", "Teststadt", "Ankunftstadt",
                getCurrentDatePlusOneWeek(), "3", "Token");
        assertNotNull(tripManager.getTripList());
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