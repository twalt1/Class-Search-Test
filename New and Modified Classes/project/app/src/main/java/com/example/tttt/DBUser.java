package com.example.tttt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBUser extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "User.db";
    public static final String TABLE_NAME = "User_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "USERNAME";
    public static final String COL_3 = "PASSWORD";
    public static final String COL_4 = "EMAIL";
    public static final String COL_5 = "AGE";
    public static final String COL_6 = "PHONENUMBER";

    UserAccount user;


    public DBUser(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT," +" USERNAME TEXT, PASSWORD TEXT, EMAIL TEXT, AGE TEXT, PHONENUMBER TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(GymMember gymMember) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, gymMember.getUserName());
        contentValues.put(COL_3, gymMember.getPassWord());
        contentValues.put(COL_4, gymMember.getEmail());
        contentValues.put(COL_5, gymMember.getAge());
        contentValues.put(COL_6, gymMember.getPhoneNo());
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public UserAccount getUser() {

        return user;

    }

    public Boolean checkusername(String username) {

        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM User_table where USERNAME= '" +username + "'", null);

        if(cursor.getCount() > 0) {

            return true;

        }   else {

            return false;

        }

    }

    public Boolean checkusernamepassword(String username, String password) {

        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM User_table where USERNAME= '" +username+ "' and PASSWORD= '" +password + "'", null);

        if(cursor.getCount() > 0) {

            return true;

        }   else {

            return false;

        }

    }

    public Integer deleteData (String id) {

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});

    }

    public Cursor getAllData() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from User_table", null);
        return res;

    }

}
