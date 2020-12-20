package com.tickshareba.persistence;

import com.tickshareba.models.TripModel;
import com.tickshareba.models.UserModel;

public interface IUserPersistenceManager {
    //TODO: add Javadoc descriptions here

    /**
     *
     * @param userModel
     * @return
     */
    public boolean persistUser(UserModel userModel);

    /**
     *
     * @param userToken
     * @return
     */
    public UserModel getUser(String userToken);

    /**
     *
     * @param userModel
     * @return
     */
    public boolean deleteUser(UserModel userModel);

}
