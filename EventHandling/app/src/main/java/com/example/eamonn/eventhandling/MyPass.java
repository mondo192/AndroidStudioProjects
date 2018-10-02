package com.example.eamonn.eventhandling;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MyPass extends Activity implements View.OnClickListener {
    Button btn;

    @Override
    public void onCreate(Bundle icicle) {
        // other on create code goes here

        // 1.
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(this);


        // 2.
        // anonymous class
        // advantage -> easier to read code, no shared on click
        // disadvantage -> cant reuse code, performance is not optimal
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // whatever you want to happen when button is clicked goes here
                    }
                }
        );

        // 3. XML onClick method with ID specified in XML
        // go to different piece of code to find "showToast" on multiple screens
        // can be difficult to maintain
        Toast.makeText(.getApplicationContext(), "Button is clicked!", Toast.LENGTH_LONG).show();

    }

    public void onClick(View view) {
        // do whatever code here
        // if else logic to distinguish different buttons

        // useful method in View class getId() returns and int


    }
}
