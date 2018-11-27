package com.example.eamonn.myapplication;

import android.app.ListActivity;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    private DatabaseHandler db;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = findViewById(R.id.list);

        try {
            db = new DatabaseHandler(this);
            db.open();
            db.insertTask("Task 1", "Eat", 1);
            Cursor cursor = db.getAllTasks();
            Log.i("test", "number of rows returned are " + cursor.getCount());
            cursor.moveToFirst();
            String[] columns = new String[] {
                    cursor.getColumnName(1),
                    cursor.getColumnName(2),
                    cursor.getColumnName(3)
            };
            int[] rowIDS = new int[] {
                    R.id.taskName,
                    R.id.taskDescription,
                    R.id.status
            };
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                    this,
                    R.layout.row,
                    cursor,
                    columns,
                    rowIDS,
                    0
            );
            listview.setAdapter(adapter);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }
}
