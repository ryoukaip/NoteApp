package com.tdmuproj.noteapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelperPassword extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "passworddb";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "password";
    public static final String PASSWORD_COLUMN = "password";
    public static final String ID_COLUMN = "id";
    public static final String BOOLEAN_COLUMN = "boolean";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + ID_COLUMN + " INTEGER PRIMARY KEY, " + PASSWORD_COLUMN + " TEXT, " + BOOLEAN_COLUMN + " TEXT)";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public DBHelperPassword(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        // add id = 1, password = 1234, boolean = "false" if the table is empty
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + ID_COLUMN + ", " + PASSWORD_COLUMN + ", " + BOOLEAN_COLUMN + ") VALUES (1, '1234', 'false')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
    public void updatePasswordToDB(String password){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + " SET " + PASSWORD_COLUMN + " = '" + password + "' WHERE " + ID_COLUMN + " = 1");
    }
    public String getPasswordFromDB(){
        SQLiteDatabase db = getReadableDatabase();
        String password = "";
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + " = 1";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            password = cursor.getString(cursor.getColumnIndexOrThrow(PASSWORD_COLUMN));
        }
        cursor.close();
        return password;
    }
    public void updateBooleanToDB(String bool){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + " SET " + BOOLEAN_COLUMN + " = '" + bool + "' WHERE " + ID_COLUMN + " = 1");
    }
    public void getBooleanFromDB(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + " = 1";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            String bool = cursor.getString(cursor.getColumnIndexOrThrow(BOOLEAN_COLUMN));
            if (bool.equals("true")){
                ControlFlowSingleton.isPasswordLocked = true;
            } else {
                ControlFlowSingleton.isPasswordLocked = false;
            }
        }
        cursor.close();
    }
}
