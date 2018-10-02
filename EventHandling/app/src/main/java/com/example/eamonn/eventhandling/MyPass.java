package com.example.eamonn.eventhandling;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import View.OnClickListener;

public class MyPass extends Activity implements View.OnClickListener {
    Button btn;

    @Override
    public void onCreate(Bundle b) {
        // on create code
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(this);
    }

    public void onClick(View view) {
        // do whatever code here
    }
}
