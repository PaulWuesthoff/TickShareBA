package com.tickshareba.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tickshareba.models.TripModel;
import com.tickshareba.models.UserModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.tickshareba.persistence.FeedReaderContract.CREATE_TABLE;
import static com.tickshareba.persistence.FeedReaderContract.DROP_TABLE;

public class PersistenceManagerImpl implements IPersistenceManager {

    private static final Logger LOG = LogManager.getLogger(PersistenceManagerImpl.class);

    private FeedReaderDBHelper feedReaderDBHelper;

    public PersistenceManagerImpl(Context context){
        feedReaderDBHelper = new FeedReaderDBHelper(context);
    }


    @Override
    public boolean persistUser(UserModel userModel) {
        SQLiteDatabase database = feedReaderDBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.TABLE_NAME, "user");
        values.put(FeedReaderContract.FeedEntry.COL_NAME, userModel.getName());
        values.put(FeedReaderContract.FeedEntry.COL_LASTNAME, userModel.getLastName());
        values.put(FeedReaderContract.FeedEntry.COL_REGION, userModel.getRegion());
        values.put(FeedReaderContract.FeedEntry.COL_EMAILADDRESS, userModel.getEmailAddress());
        values.put(FeedReaderContract.FeedEntry.COL_PASSWORDHASH, userModel.getPassword());
        values.put(FeedReaderContract.FeedEntry.COL_TOKEN, userModel.getToken());
        values.put(FeedReaderContract.FeedEntry.COL_SALT, userModel.getSalt());

        long newRowID = database.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
        return newRowID == -1;
    }

    @Override
    public boolean persistTrip(TripModel tripModel) {
        return false;
    }

    @Override
    public UserModel getUser(String userToken) {
        return null;
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
