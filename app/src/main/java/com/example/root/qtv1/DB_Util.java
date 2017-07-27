package com.example.root.qtv1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Util extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userDB.db";
    private static final String TABLE_USERS = "users";

    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PW = "password";

    private static final String CREATE_USER_TABLE =
            "CREATE TABLE " + TABLE_USERS +
            "(" + COLUMN_USERNAME + "TEXT PRIMARY KEY" + COLUMN_PW + "TEXT" + ")";


    DB_Util(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

}