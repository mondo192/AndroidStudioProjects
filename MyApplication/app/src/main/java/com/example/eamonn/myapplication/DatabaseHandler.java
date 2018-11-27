package com.example.eamonn.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

public class DatabaseHandler {

    // database columns
    private static final String KEY_ROWID = "_id";
    private static final String TASK_NAME = "task_name";
    private static final String TASK_DESCRIPTION = "task_description";
    private static final String STATUS = "status";
    private static final String DATABASE_NAME = "TaskLists2";
    private static final String DATABASE_TABLE = "Tasks";
    private static final int DATABASE_VERSION = 1;

    // SQL statement to create the database
    private static final String DATABASE_CREATE =
            "CREATE TABLE Tasks ( " +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "task_name TEXT NOT NULL, " +
                    "task_description TEXT NOT NULL, " +
                    "status INTEGER NOT NULL );";

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    // constructor
    public DatabaseHandler(Context context) {
        this.context = context;
        DBHelper = new DatabaseHelper(context);
    }

    public DatabaseHandler open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        DBHelper.close();
    }

    public long insertTask(String task_name, String task_description, int status) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(TASK_NAME, task_name);
        initialValues.put(TASK_DESCRIPTION, task_description);
        initialValues.put(STATUS, status);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    public boolean deleteTask(long rowID) {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowID, null) > 0;
    }

    public Cursor getAllTasks() {
        return db.query(DATABASE_TABLE, new String[]
                        {
                                KEY_ROWID,
                                TASK_NAME,
                                TASK_DESCRIPTION,
                                STATUS
                        },
                null, null, null, null, null
        );
    }

    public Cursor getTask(long rowID) throws SQLException {
        Cursor myCursor = db.query(true, DATABASE_TABLE, new String[]
                        {
                                KEY_ROWID,
                                TASK_NAME,
                                TASK_DESCRIPTION,
                                STATUS
                        },
                KEY_ROWID + "=" + rowID, null, null, null, null, null
        );

        if (myCursor != null) {
            myCursor.moveToFirst();
        }
        return myCursor;
    }

    public boolean updateTask(long rowID, String task_name, String task_description, int status) {
        ContentValues args = new ContentValues();
        args.put(TASK_NAME, task_name);
        args.put(TASK_DESCRIPTION, task_description);
        args.put(STATUS, status);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowID, null) > 0;
    }


    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
