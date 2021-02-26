package com.tickshareba.management;

import com.tickshareba.models.TripModel;

public interface ITripManager{
//TODO: add javadoc descriptions later
    /**
     *
     * @param startingLocation
     * @param destination
     * @param startingTime
     * @param seatsLeft
     * @param userToken
     * @return
     */
    public boolean createTrip(String ID, String startingLocation, String destination, String startingTime, String seatsLeft, String userToken);

    /**
     *
     * @param startingLocation
     * @param destination
     * @param startingTime
     * @param seatsLeft
     * @return
     */
    public boolean createTripWithoutUserToken(String startingLocation, String destination, String startingTime, String seatsLeft);

}
