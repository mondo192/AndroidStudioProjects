package com.example.eamonn.eventhandling;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyPass extends Activity implements View.OnClickListener {
    Button btn;

    @Override
    public void onCreate(Bundle icicle) {
        // other on create code goes here


        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(this);
    }

    public void onClick(View view) {
        // do whatever code here
        // if else logic to distingush different buttons

        // useful method in View class getId() returns and int


    }
}
