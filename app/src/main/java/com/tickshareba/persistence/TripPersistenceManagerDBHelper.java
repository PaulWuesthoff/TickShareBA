package com.tickshareba.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.tickshareba.models.TripModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TripPersistenceManagerDBHelper extends SQLiteOpenHelper implements ITripPersistenceManager {

    private static final Logger LOG = LogManager.getLogger(TripPersistenceManagerDBHelper.class);
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Trip.db";

    private static final String TABLE_NAME = "trip";
    private static final String COL_ID = "ID";
    private static final String COL_STARTINGLOCATION = "startinglocation";
    private static final String COL_DESTINATION = "destination";
    private static final String COL_STARTINGTIME = "startingtime";
    private static final String COL_SEATSLEFT = "seatsleft";
    private static final String COL_USERTOKEN = "usertoken";

    public TripPersistenceManagerDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        LOG.info("###### Create Table");
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_STARTINGLOCATION + " TEXT, " + COL_DESTINATION + " TEXT," + COL_STARTINGTIME + " TEXT, " +
                COL_SEATSLEFT + " TEXT, " + COL_USERTOKEN + " TEXT " + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }


    @Override
    public boolean persistTrip(TripModel tripModel) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_STARTINGLOCATION, tripModel.getStartingLocation());
        contentValues.put(COL_DESTINATION, tripModel.getDestination());
        contentValues.put(COL_STARTINGTIME, tripModel.getStartingLocation());
        contentValues.put(COL_SEATSLEFT, tripModel.getSeatsLeft());
        contentValues.put(COL_USERTOKEN, tripModel.getUserToken());
        long result = database.insert(TABLE_NAME, null, contentValues);

        return result != -1;


    }

    @Override
    public TripModel getTrip(String userToken) {
        SQLiteDatabase database = this.getReadableDatabase();
        String querry = "SELECT " + COL_STARTINGLOCATION + ", " + COL_DESTINATION + ", " + COL_STARTINGTIME + ", "
                + COL_SEATSLEFT + ", " + COL_USERTOKEN + " FROM " + TABLE_NAME + " WHERE " + COL_USERTOKEN + " = " + "'" + userToken + "'" + ";";

        Cursor data = database.rawQuery(querry, null);

        List<String> tripInformation = new ArrayList<>();

        while (data.moveToNext()) {
            tripInformation.add(data.getString(0));
            tripInformation.add(data.getString(1));
            tripInformation.add(data.getString(2));
            tripInformation.add(data.getString(3));
            tripInformation.add(data.getString(4));
        }

        System.out.println(tripInformation.toString());


        TripModel model = null;
        if (tripInformation.get(4) != null) {
            model = new TripModel(tripInformation.get(0), tripInformation.get(1), tripInformation.get(2), tripInformation.get(3));
        } else {
            model = new TripModel(getLastID(), tripInformation.get(1), tripInformation.get(2), tripInformation.get(3), tripInformation.get(4), tripInformation.get(5));
        }


        return model;
    }

    @Override
    public boolean deleteTrip(TripModel tripModel) {
        return false;
    }

    private String getLastID() {
        SQLiteDatabase database = this.getReadableDatabase();
        String selectLastInsertedId = "SELECT last_insert_rowid();";

        Cursor data = database.rawQuery(selectLastInsertedId, null);
        List<String> lastId = new ArrayList<>();
        while (data.moveToNext()) {
            lastId.add(data.getString(0));
        }

        LOG.info("Last ID: " + lastId.get(0));
        return lastId.get(0);
    }
}
