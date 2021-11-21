package com.example.seg2105;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_USER_AGE = "USER_AGE";
    public static final String COLUMN_USER_EMAIL = "USER_EMAIL";
    public static final String COLUMN_USER_PHONE = "USER_PHONE";
    public static final String COLUMN_USER_USERNAME = "USER_USERNAME";
    public static final String COLUMN_USER_PASSWORD = "USER_PASSWORD";
    public static final String COLUMN_USER_ACCTTYPE = "USER_ACCTTYPE";

    public Database(@Nullable Context context) {
        super(context, "user.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + USER_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_NAME + " TEXT, " + COLUMN_USER_AGE + " INT, " + COLUMN_USER_EMAIL + " TEXT, " + COLUMN_USER_PHONE + " TEXT, " + COLUMN_USER_USERNAME + " TEXT, " + COLUMN_USER_PASSWORD + " TEXT, " + COLUMN_USER_ACCTTYPE + " TEXT)";

        db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addUser(UserAccount userAccount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_EMAIL, userAccount.getEmail());
        cv.put(COLUMN_USER_PASSWORD, userAccount.getPassWord());

        long insert = db.insert(USER_TABLE, null, cv);
        if (insert == -1){
            return false;
        }
        else {
            return true;
        }
    }
}
