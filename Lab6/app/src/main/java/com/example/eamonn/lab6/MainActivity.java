package com.example.eamonn.lab6;

import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private DatabaseTL db = new DatabaseTL(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            db.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.insertTask("Flip", "Doing a flip backwards", 0);
        db.insertTask("Task 2", "This is my new task", 0);
        db.insertTask("Task 3", "This is my next task", 0);
        db.close();
    }
}
