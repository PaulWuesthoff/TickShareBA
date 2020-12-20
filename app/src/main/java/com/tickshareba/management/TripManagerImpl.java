package com.tickshareba.management;

import com.tickshareba.models.TripModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class TripManagerImpl implements ITripManager {

    private static final Logger LOG = LogManager.getLogger(TripManagerImpl.class);

    @Getter
    private List<TripModel> tripList = new ArrayList<>();

    @Override
    public boolean createTrip(String startingLocation, String destination, String startingTime, String seatsLeft, String userToken) {
        return tripList.add(new TripModel(startingLocation, destination, startingTime, seatsLeft, userToken));
    }

    @Override
    public boolean createTripWithouUserToken(String startingLocation, String destination, String startingTime, String seatsLeft) {
        return tripList.add(new TripModel(startingLocation, destination, startingTime, seatsLeft));
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
