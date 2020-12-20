package com.tickshareba.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tickshareba.models.TripModel;
import com.tickshareba.models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserPersistenceManagerDBHelper extends SQLiteOpenHelper implements IUserPersistenceManager {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Users.db";

    private final static String TABLE_NAME = "user";
    private final static String COL_ID = "ID";
    private final static String COL_NAME = "name";
    private final static String COL_LASTNAME = "lastname";
    private final static String COL_REGION = "region";
    private final static String COL_EMAILADDRESS = "emailaddress";
    private final static String COL_PASSWORDHASH = "passwordhash";
    private final static String COL_TOKEN = "usertoken";
    private final static String COL_SALT = "salt";

    public UserPersistenceManagerDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " TEXT, " + COL_LASTNAME + " TEXT," + COL_REGION + " TEXT, " +
                COL_EMAILADDRESS + " TEXT, " + COL_PASSWORDHASH + " TEXT, " +
                COL_TOKEN + " TEXT, " + COL_SALT + " TEXT " + ")";
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

    /**
     * Will return -1 if something went wrong and > -1 if everything went well
     *
     * @param userModel
     * @return
     */
    @Override
    public boolean persistUser(UserModel userModel) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, userModel.getName());
        contentValues.put(COL_LASTNAME, userModel.getLastName());
        contentValues.put(COL_REGION, userModel.getRegion());
        contentValues.put(COL_EMAILADDRESS, userModel.getEmailAddress());
        contentValues.put(COL_PASSWORDHASH, userModel.getPassword());
        contentValues.put(COL_TOKEN, userModel.getToken());
        contentValues.put(COL_SALT, userModel.getSalt());

        long result = database.insert(TABLE_NAME, null, contentValues);

        return result != -1;
    }

    public UserModel getUser(String emailAddress) {
        SQLiteDatabase database = this.getReadableDatabase();
        String querry = "SELECT " + COL_NAME + ", " + COL_LASTNAME + ", " + COL_REGION + ", "
                + COL_EMAILADDRESS + ", " + COL_PASSWORDHASH + ", " + COL_TOKEN + ", "
                + COL_SALT + " FROM " + TABLE_NAME + " WHERE " + COL_EMAILADDRESS + " = "
                + "'" + emailAddress + "'" + ";";

        Cursor data = database.rawQuery(querry, null);

        List<String> userInformation = new ArrayList<>();

        while (data.moveToNext()) {
            userInformation.add(data.getString(0));
            userInformation.add(data.getString(1));
            userInformation.add(data.getString(2));
            userInformation.add(data.getString(3));
            userInformation.add(data.getString(4));
            userInformation.add(data.getString(5));
            userInformation.add(data.getString(6));
        }

        System.out.println(userInformation.toString());
        UserModel model = new UserModel(userInformation.get(0), userInformation.get(1), userInformation.get(2), userInformation.get(3), userInformation.get(4), userInformation.get(5), userInformation.get(6));

        return model;
    }

    @Override
    public boolean deleteUser(UserModel userModel) {
        return false;
    }

}
