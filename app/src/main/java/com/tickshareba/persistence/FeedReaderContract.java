package com.tickshareba.persistence;

import android.provider.BaseColumns;

public final class FeedReaderContract {
    private FeedReaderContract(){}

    static final String CREATE_TABLE = "CREATE TABLE " + FeedEntry.TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FeedEntry.COL_NAME + " TEXT, " + FeedEntry.COL_LASTNAME + " TEXT," + FeedEntry.COL_REGION + " TEXT, " +
            FeedEntry.COL_EMAILADDRESS + " TEXT, " + FeedEntry.COL_PASSWORDHASH + " TEXT, " +
            FeedEntry.COL_TOKEN + " TEXT, " + FeedEntry.COL_SALT + " TEXT " + ")";
    static final String DROP_TABLE = "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

    public static class FeedEntry implements BaseColumns{
         final static String TABLE_NAME = "user";
         final static String COL_ID = "ID";
         final static String COL_NAME = "name";
         final static String COL_LASTNAME = "lastname";
         final static String COL_REGION = "region";
         final static String COL_EMAILADDRESS = "emailaddress";
         final static String COL_PASSWORDHASH = "passwordhash";
         final static String COL_TOKEN = "usertoken";
         final static String COL_SALT = "salt";
    }
}
