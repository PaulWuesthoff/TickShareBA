package com.example.tickshareba.management;

import com.example.tickshareba.models.TripModel;

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
    public boolean createTrip(String startingLocation, String destination, String startingTime, String seatsLeft, String userToken);

    /**
     *
     * @param startingLocation
     * @param destination
     * @param startingTime
     * @param seatsLeft
     * @return
     */
    public boolean createTripWithouUserToken(String startingLocation, String destination, String startingTime, String seatsLeft);

    /**
     *
     * @param trip
     * @return
     */
    public boolean persistTrip(TripModel trip);

    /**
     *
     * @param trip
     * @return
     */
    public boolean deleteTrip(TripModel trip);


}
