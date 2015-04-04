package com.tarapus.android.friends;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by andreytarasenko on 3/30/15.
 */
public class FriendsDatabase extends SQLiteOpenHelper {

    private static final String TAG = FriendsDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "friends.db";
    private static final int DATABASE_VERSION = 2; //Needs to be 2 for Upgrades
    private final Context mContext; //Store context

    interface Tables {
        String FRIENDS = "friends";
    }

    public FriendsDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;

    }


}
