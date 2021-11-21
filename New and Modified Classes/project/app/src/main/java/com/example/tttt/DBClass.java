package com.example.tttt;

import static android.os.Build.ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBClass extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Class.db";
    public static final String TABLE_NAME = "Class_table";
    public static final String COL_ID = "ID";
    public static final String COL_TYPE = "Type";
    public static final String COL_1 = "Title";
    public static final String COL_2 = "Description";
    public static final String COL_3 = "Difficulty";
    public static final String COL_4 = "Capacity";
    public static final String COL_5 = "Date";
    public static final String COL_6 = "Time";
    public static final String COL_INSTRUCTOR = "Instructor";
    public static final String COL_DAYOFWEEK = "DayOfWeek";
    //public static final String COL_7 = "Status";
    Class newclass;

    public DBClass(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                + "(" + COL_ID + " INTEGER PRIMARY KEY,"
                + COL_1 + " TEXT,"
                + COL_TYPE + " TEXT,"
                + COL_2 + " TEXT,"
                + COL_3 + " TEXT,"
                + COL_4 + " TEXT,"
                + COL_5 + " TEXT,"
                + COL_6 + " TEXT,"
                + COL_INSTRUCTOR + " TEXT,"
                + COL_DAYOFWEEK + " TEXT" +
                ")";

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }


    public boolean insertData(String title, String type, String description, String difficulty, Integer capacity, String date, String time, String instructor, String dayOfWeek) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, title);
        contentValues.put(COL_TYPE, type);
        contentValues.put(COL_2, description);
        contentValues.put(COL_3, difficulty);
        contentValues.put(COL_4, capacity);
        contentValues.put(COL_5, date);
        contentValues.put(COL_6, time);
        contentValues.put(COL_INSTRUCTOR, instructor);
        contentValues.put(COL_DAYOFWEEK, dayOfWeek);

        newclass = new Class(title, type, description, difficulty, capacity, date, time, instructor, dayOfWeek);

        long res = db.insert(TABLE_NAME, null, contentValues);
        db.close();

        if(res == -1) {

            return false;

        }   else {

            return true;

        }


    }

    //public Integer deleteData (String id) {

    //    SQLiteDatabase db = this.getWritableDatabase();
    //    db.close();
    //    return db.delete(TABLE_NAME, "ID = ?", new String[] {id});

    //}

    public boolean deleteData(String id) {

        SQLiteDatabase db = this.getWritableDatabase();
        boolean res = false;

        String query = "SELECT * FROM " + TABLE_NAME
                + " WHERE " + COL_ID
                + " =\"" + id + "\"";

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {

            String str = cursor.getString(0);
            db.delete(TABLE_NAME, COL_ID + " = " + str, null);
            cursor.close();
            res = true;

        }

        db.close();

        return res;

    }

    public Cursor getAllData() {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        return cursor;

    }

    public boolean updateName(String id, String name){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1, name);
        database.update(TABLE_NAME, cv, "ID = ?", new String[] { id });
        return true;
    }

    public boolean updateDescription(String id, String description){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2, description);
        database.update(TABLE_NAME, cv, "ID = ?", new String[] { id });
        return true;
    }

    public void updateCapacity(String id, int capacity){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_4, capacity);
        database.update(TABLE_NAME, cv, "ID = ?", new String[] { id });
    }

    public void updateDate(String id, String date){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_5, date);
        database.update(TABLE_NAME, cv, "ID = ?", new String[] { id });
    }

    public void updateTime(String id, String time){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_6, time);
        database.update(TABLE_NAME, cv, "ID = ?", new String[] { id });
    }

    public void updateDifficulty(String id, String difficulty){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_3, difficulty);
        database.update(TABLE_NAME, cv, "ID = ?", new String[] { id });
    }

    public void updateType(String id, String type){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_TYPE, type);
        database.update(TABLE_NAME, cv, "ID = ?", new String[] { id });
    }

    public void updateDayOfWeek(String id, String dayOfWeek){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_DAYOFWEEK, dayOfWeek);
        database.update(TABLE_NAME, cv, "ID = ?", new String[] { id });
    }

    /** To store a class to the database.
     *
     * @param aClass Type Class
     * @return True if successfully added; False otherwise
     */

    public boolean addClass(Class aClass) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, aClass.getTitle());
        contentValues.put(COL_TYPE, aClass.getType());
        contentValues.put(COL_2, aClass.getDescription());
        contentValues.put(COL_3, aClass.getDifficulty());
        contentValues.put(COL_4, aClass.getCapacity());
        contentValues.put(COL_5, aClass.getDate());
        contentValues.put(COL_6, aClass.getTime());
        contentValues.put(COL_INSTRUCTOR, aClass.getInstructor());
        contentValues.put(COL_DAYOFWEEK, aClass.getDayOfWeek());

        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();

        return result != -1;

    }

    /** Check whether conflict exists
     *
     * @param type Type of class
     * @param date Date of class
     * @return True if no conflict; False otherwise
     */

    public boolean checkExist(String type, String date) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+COL_TYPE+"=?" +" AND " + COL_5+"=?", new String[] {type,date});
        boolean res = !(c.getCount() > 0);
        c.close();

        return res;

    }

    public Integer getNum(String type, String date) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+COL_TYPE+"=?" +" AND " + COL_5+"=?", new String[] {type,date});
        return c.getCount();

    }

    /** To find a class in the database, return a empty class if not exist.
     *
     * @param title The title of the class (!Have not tested yet!)
     * @return  a type Class
     */

    public Class findClass(String title) {

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME
                + " WHERE " + COL_1
                + " = \"" + title + "\"";

        Cursor cursor = db.rawQuery(query, null);
        Class aClass = new Class();

        if (cursor.moveToFirst()) {

            aClass.setTitle(cursor.getString(0));
            aClass.setType(cursor.getString(1));
            aClass.setDescription(cursor.getString(2));
            aClass.setDifficulty(cursor.getString(3));
            aClass.setCapacity(Integer.parseInt(cursor.getString(4)));
            aClass.setDate(cursor.getString(5));
            aClass.setTime(cursor.getString(6));
            aClass.setInstructor(cursor.getString(7));
            aClass.setDayOfWeek(cursor.getString(8));
            cursor.close();

        }   else {

            aClass = null;

        }

        db.close();

        return aClass;

    }

    /** To delete a class by searching title.
     *
     * @param title A String as a title of the class (!Have not tested yet!)
     * @return  True if deleteClass successful; False otherwise
     */

    public boolean deleteClass(String title) {

        boolean res = false;

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME
                + " WHERE " + COL_1
                + " = \"" + title + "\"";

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {

            String titleStr = cursor.getString(0);
            db.delete(TABLE_NAME, COL_1 + " = " + titleStr, null);
            cursor.close();
            res = true;

        }

        db.close();

        return res;

    }

}