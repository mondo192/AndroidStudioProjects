package com.example.eamonn.lab6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseTL {

    // database columns
    private static final String KEY_ROWID = "_id";
    private static final String TASK_NAME = "task_name";
    private static final String TASK_DESC = "task_desc";
    private static final String STATUS = "status";
    private static final String DATABASE_NAME = "TaskLists";
    private static final String DATABASE_TABLE = "Tasks";
    private static final int DATABASE_VERSION 	= 1;

    // SQL statement to create the database
    private static final String DATABASE_CREATE =
            "create table Tasks ( " +
                    "id integer primary key autoincrement, " +
                    "task_name text not null, " +
                    "task_desc text not null, "  +
                    "status integer not null);";

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    // Constructor
    public DatabaseTL(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    public DatabaseTL open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        DBHelper.close();
    }

    public long insertTask(String task_name, String task_desc, int status)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(TASK_NAME, task_name);
        initialValues.put(TASK_DESC, task_desc);
        initialValues.put(STATUS, status);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    public boolean deleteTask(long rowId)
    {

        return db.delete(DATABASE_TABLE, KEY_ROWID +
                "=" + rowId, null) > 0;
    }

    public Cursor getAllTasks()
    {
        return db.query(DATABASE_TABLE, new String[]
                        {
                                KEY_ROWID,
                                TASK_NAME,
                                TASK_DESC,
                                STATUS
                        },
                null, null, null, null, null);
    }

    public Cursor getTask(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[]
                                {
                                        KEY_ROWID,
                                        TASK_NAME,
                                        TASK_DESC,
                                        STATUS
                                },
                        KEY_ROWID + "=" + rowId,  null, null, null, null, null);

        if (mCursor != null)
        {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //
    public boolean updateTask(long rowId, String task_name, String task_desc, int status)
    {
        ContentValues args = new ContentValues();
        args.put(TASK_NAME, task_name);
        args.put(TASK_DESC, task_desc);
        args.put(STATUS, status);
        return db.update(DATABASE_TABLE, args,
                KEY_ROWID + "=" + rowId, null) > 0;
    }

    // ///////////////////////nested dB helper class ///////////////////////////////////////
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        //
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        //
        public void onCreate(SQLiteDatabase db)
        {

            db.execSQL(DATABASE_CREATE);
        }

        @Override
        //
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion)
        {
            // dB structure change..

        }
    }
    //////////////////////////// end nested dB helper class //////////////////////////////////////

}

