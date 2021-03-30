package com.tickshareba.persistence;

import com.tickshareba.models.TripModel;

import java.util.List;

public interface ITripPersistenceManager {


    /**
     *
     * @param tripModel
     * @return
     */
    public boolean persistTrip(TripModel tripModel);

    /**
     *
     * @param userToken
     * @return
     */
    public TripModel getTrip(String userToken);

    /**
     *
     * @param ID
     * @return
     */
    public TripModel getTripWithID(String ID);

    /**
     *
     * @param ID
     * @param tripModel
     */
    public void update(String ID, TripModel tripModel);

    /**
     *
     * @param tripModel
     * @return
     */
    public boolean deleteTrip(TripModel tripModel);

    /**
     *
     */
    public void deleteOldTrips();

    /**
     *
     * @return
     */
    public List<TripModel> getAllTrips();
}
