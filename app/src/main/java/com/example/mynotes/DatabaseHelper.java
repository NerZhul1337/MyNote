package com.example.mynotes;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "NoteStore.db";
    private static final int SCHEMA = 1;
    static final String TABLE = "Note";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOTE_TEXT = "note_text";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE + " (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NOTE_TEXT
                + " TEXT);");

        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NOTE_TEXT
                + ") VALUES ('Первая запись');");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,	int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}