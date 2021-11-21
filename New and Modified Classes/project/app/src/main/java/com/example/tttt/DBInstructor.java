package com.example.tttt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBInstructor extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Instructor.db";
    public static final String TABLE_NAME = "Instructor_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "USERNAME";
    public static final String COL_3 = "PASSWORD";
    public static final String COL_4 = "EMAIL";
    public static final String COL_5 = "AGE";
    public static final String COL_6 = "PHONENUMBER";
    UserAccount user;


    public DBInstructor(@Nullable Context context) {
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

    public boolean insertData(Instructor instructor) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, instructor.getUserName());
        contentValues.put(COL_3, instructor.getPassWord());
        contentValues.put(COL_4, instructor.getEmail());
        contentValues.put(COL_5, instructor.getAge());
        contentValues.put(COL_6, instructor.getPhoneNo());
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public UserAccount getUser() {

        return user;

    }

    public Boolean checkusername(String username) {

        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM Instructor_table where USERNAME= '" +username + "'", null);

        if(cursor.getCount() > 0) {

            return true;

        }   else {

            return false;

        }

    }

    public Boolean checkusernamepassword(String username, String password) {

        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM Instructor_table where USERNAME= '" +username+ "' and PASSWORD= '" + password + "'", null);

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

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from Instructor_table", null);
        return res;

    }


    public Instructor getInstructorFromUsernameAndPassword(String username_in, String password_in){
        //return instructor based on username and password
        Instructor instructor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from " + TABLE_NAME + " ";
        query += " where USERNAME = '" + username_in + "' ";
        query += " and PASSWORD = '" + password_in + "' ";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            String username = cursor.getString(1);
            String password = cursor.getString(2);
            String email = cursor.getString(3);
            String age = cursor.getString(4);
            String phoneNumber = cursor.getString(5);
            instructor = new Instructor(username, password, email, age, phoneNumber);
        }

        cursor.close();
        db.close();
        return instructor;
    }

}
