package com.example.davidperez.theapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by davidperez on 2/17/2015.
 */


//Testing speech recognition code in this file, will change later!!!
public class NewRouteActivity extends ActionBarActivity {
    //constants for requests when calling speech recognizer,
    //can add different ones in order to prompt speech recognizer to look for different things
    static final int DEFAULT_SPEECH_REQUEST = 1;

    private EditText nameField;
    private EditText startPointField;
    private EditText endPointField;
    private Button saveRoute;
    private DummyRoute route;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_route);

        saveRoute = (Button) findViewById(R.id.button_save_route);
        nameField = (EditText) findViewById(R.id.newRouteName);
        startPointField = (EditText) findViewById(R.id.newRouteStartPoint);
        endPointField = (EditText) findViewById(R.id.newRouteEndPoint);

        Button recog = (Button) findViewById(R.id.recogButton);
        /*recog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent recogIntent = new Intent(view.getContext(), SRecognizer.class);
                startActivityForResult(recogIntent, DEFAULT_SPEECH_REQUEST);
            }
        });*/

        saveRoute.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!(nameField.getText().toString().isEmpty())
                      && !(startPointField.getText().toString().isEmpty())
                      && !(endPointField.getText().toString().isEmpty())
                   )
                {
                    DummyRouteCollection list = new DummyRouteCollection();
                    list = list.readRoutes(getApplicationContext());
                    route = new DummyRoute();
                    route.setName(nameField.getText().toString());
                    route.setStartPoint(startPointField.getText().toString());
                    route.setEndPoint(endPointField.getText().toString());
                    list.addRoute(route);
                    list.writeRoutes(getApplicationContext());
                    finish();
                }
            }
        });

    }
}
