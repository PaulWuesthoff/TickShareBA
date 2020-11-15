package com.example.tickshareba.persistence;

import com.example.tickshareba.models.TripModel;
import com.example.tickshareba.models.UserModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PersistenceManagerImpl implements IPersistenceManager{

    private static final Logger LOG = LogManager.getLogger(PersistenceManagerImpl.class);

    @Override
    public boolean persistUser(UserModel userModel) {
        return false;
    }

    @Override
    public boolean persistTrip(TripModel tripModel) {
        return false;
    }

    @Override
    public boolean getUser(String userToken) {
        return false;
    }

    @Override
    public boolean getTrip(String userToken) {
        return false;
    }

    @Override
    public boolean deleteUser(UserModel userModel) {
        return false;
    }

    @Override
    public boolean deleteTrip(TripModel tripModel) {
        return false;
    }
}
