package com.example.eamonn.lab7;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {

    DatabaseTL db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseTL(this);

        try {
            db.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.insertTask("Task 2", "Eat breakfast", 1);
        db.insertTask("Task 1", "Finish android lab", 0);

        Cursor c = db.getAllTasks();
        Log.i("test", "number of rows returned are " + c.getCount());

        c.moveToFirst();

        String[] columns = new String[] {"task_name", "task_description", "status"};
        int[] rowIDs = new int[] {R.id.taskName, R.id.taskDescription, R.id.status};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.row, c, columns, rowIDs);
        setListAdapter(adapter);

        db.close();
    }


}
