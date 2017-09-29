package com.example.adityapattani.gitfit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aditya Pattani on 19-04-2017.
 */

public class DBHelper {
    private SQLiteDatabase db;
    private final Context context;
    private MyDBAdapter helper;

    public static final String DATABASE_NAME = "Exercise.db";
    public static final int DATABASE_VERSION = 3;
    public static final String USERNAME = "username";
    public static final String PASSWORD = "pass";
    public static final String AGE = "age";
    public static final String TABLE_USERS = "users";

    public static final String TABLE_EXERCISES = "exercises";
    public static final String EXERCISE_NAME = "name";
    public static final String EXERCISE_FOR = "for";

    public static final String CREATE_DB_USERS = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + "(" + USERNAME+ " VARCHAR(15), " + PASSWORD + " VARCHAR(15), " + AGE +" NUMBER(3));";
    public static final String CREATE_DB_EXERCISES = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + "(" + USERNAME+ " VARCHAR(15), " + PASSWORD + " VARCHAR(15), " + AGE +" NUMBER(3));";

    public DBHelper(Context context) {
        this.context = context;
        helper = new MyDBAdapter(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHelper open (){
        db = helper.getWritableDatabase();
        return this;
    }

    public void close(){
        db.close();
    }

    public long insertUser(String username, String password, String age) {
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase database = helper.getWritableDatabase();

        contentValues.put(USERNAME, username);
        contentValues.put(PASSWORD, password);
        contentValues.put(AGE, age);
        return database.insert(TABLE_USERS, null, contentValues);
    }

    public String getAge(String username){
        SQLiteDatabase database = helper.getWritableDatabase();
        String age = "0";
        String AgeQuery = "SELECT " + AGE + " FROM " + TABLE_USERS + " WHERE " + USERNAME + " LIKE '" + username + "';";
        Cursor cursor = database.rawQuery(AgeQuery, null);

        if (cursor.moveToFirst()){
            do {
                age = cursor.getString(cursor.getColumnIndex(AGE));
            }while (cursor.moveToNext());
        }
        return age;
    }
    
    public boolean CheckUser(String uname, String pass){
        SQLiteDatabase database = helper.getWritableDatabase();
        String Que = "Select * from " + TABLE_USERS + " where " + USERNAME + " like '" + uname + "' and " + PASSWORD + " like '" + pass + "';";
        Cursor cursor = database.rawQuery(Que, null);

        if (cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public boolean delete (String username){
        SQLiteDatabase database = helper.getWritableDatabase();
        return database.delete(TABLE_USERS, USERNAME+" like '"+username+"'", null)>0;
    }

    private static class MyDBAdapter extends SQLiteOpenHelper {
        public MyDBAdapter(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DB_USERS);
            db.execSQL(CREATE_DB_EXERCISES);
            Log.i("db", "Database created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISES);
            onCreate(db);
        }
    }
}
