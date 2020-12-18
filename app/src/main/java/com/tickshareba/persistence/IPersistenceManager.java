package com.tickshareba.persistence;

import com.tickshareba.models.TripModel;
import com.tickshareba.models.UserModel;

public interface IPersistenceManager {
    //TODO: add Javadoc descriptions here

    /**
     *
     * @param userModel
     * @return
     */
    public boolean persistUser(UserModel userModel);

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
    public UserModel getUser(String userToken);

    /**
     *
     * @param userToken
     * @return
     */
    public boolean getTrip(String userToken);

    /**
     *
     * @param userModel
     * @return
     */
    public boolean deleteUser(UserModel userModel);

    /**
     *
     * @param tripModel
     * @return
     */
    public boolean deleteTrip(TripModel tripModel);
}
