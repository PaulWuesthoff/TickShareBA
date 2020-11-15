package com.example.tickshareba.management;

import com.example.tickshareba.models.TripModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TripManagerImpl implements ITripManager {

    private static final Logger LOG = LogManager.getLogger(TripManagerImpl.class);

    @Override
    public boolean createTrip(String startingLocation, String destination, String startingTime, String seatsLeft, String userToken) {
        return false;
    }

    @Override
    public boolean createTripWithouUserToken(String startingLocation, String destination, String startingTime, String seatsLeft) {
        return false;
    }

    @Override
    public boolean persistTrip(TripModel trip) {
        return false;
    }

    @Override
    public boolean deleteTrip(TripModel trip) {
        return false;
    }
}
