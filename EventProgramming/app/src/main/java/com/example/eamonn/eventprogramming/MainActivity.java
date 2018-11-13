package com.example.eamonn.eventprogramming;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private EditText textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. using a listener interface
        btn1 = (Button) findViewById(R.id.button1);
        btn1.setOnClickListener(this);
        onClick(btn1);

        // 3. Anon class
        btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button 2 was clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        // 4. embed XML callback
        btn3 = (Button) findViewById(R.id.button3);

        // 5. Send entered text to toast
        EditText text;
        TextView label;



    }

    @Override
    public void onClick(View v) {
        if (v.getId()  == R.id.button1)
            Toast.makeText(getApplicationContext(),  "Button 1 was clicked!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), "Button 2 was clicked", Toast.LENGTH_SHORT). show();

        label.setText(text.getText().toString());
    }

    public void sendToast(View v) {
        Toast.makeText(this, "Button 3 was clicked!", Toast.LENGTH_SHORT).show();
    }

    public void sendMessage(View t) {
        String message = textView.getText().toString();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
