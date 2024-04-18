package com.tdmuproj.noteapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    /* database schema
    DATABASE_NAME = "notedb'
    DATABASE_VERSION = 1
    TABLE_NAME = "notes"
    ID_COLUMN = "id"
    NOTE_TITLE_COLUMN = "note_title"
    NOTE_CONTENT_COLUMN = "note_content"
    DATE_TIME_COLUMN = "date_time"
    */
    public static final String DATABASE_NAME = "notedb";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "notes";
    public static final String ID_COLUMN = "id";
    public static final String NOTE_TITLE_COLUMN = "note_title";
    public static final String NOTE_CONTENT_COLUMN = "note_content";
    public static final String DATE_TIME_COLUMN = "date_time";
    public long newRowId;
    public static List<Note> noteList = new ArrayList<>();

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOTE_TITLE_COLUMN + " TEXT, " +
                NOTE_CONTENT_COLUMN + " TEXT, " +
                DATE_TIME_COLUMN + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTable);
        onCreate(db);
    }

    public void addNoteToDB(String title, String content, String dateTime) {
        // get a writable database
        SQLiteDatabase db = getWritableDatabase();
        // create a new ContentValues object
        ContentValues values = new ContentValues();
        // put the title and content into the ContentValues object
        values.put(NOTE_TITLE_COLUMN, title);
        // put the content into the ContentValues object
        values.put(NOTE_CONTENT_COLUMN, content);
        values.put(DATE_TIME_COLUMN, dateTime);
        // insert the ContentValues object into the database
        newRowId = db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public void createNoteListFromDB() {
        // get a readable database
        SQLiteDatabase db = getReadableDatabase();
        // create a cursor object
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            noteList.add(new Note(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            ));
            cursor.moveToNext();
        }
        // sort noteList by index in descending order
        // noteList.sort((note1, note2) -> note2.getId() - note1.getId());
        NoteSingleton noteSingleton = NoteSingleton.getInstance();
        noteSingleton.setNoteList(noteList);
        db.close();
        cursor.close();
    }
    public void deleteNoteFromDB(String dateTime){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, DATE_TIME_COLUMN + " = ?", new String[]{dateTime});
        db.close();
    }
}
