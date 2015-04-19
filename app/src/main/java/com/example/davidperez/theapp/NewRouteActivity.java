package com.example.davidperez.theapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;


/**
 * Created by davidperez on 2/17/2015.
 */


//Testing speech recognition code in this file, will change later!!!
public class NewRouteActivity extends ActionBarActivity {
    //constants for requests when calling speech recognizer,
    //can add different ones in order to prompt speech recognizer to look for different things
    static final int DEFAULT_SPEECH_REQUEST = 1;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_route);

        Button recog = (Button) findViewById(R.id.recogButton);
        /*recog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent recogIntent = new Intent(view.getContext(), SRecognizer.class);
                startActivityForResult(recogIntent, DEFAULT_SPEECH_REQUEST);
            }
        });*/
    }
}
