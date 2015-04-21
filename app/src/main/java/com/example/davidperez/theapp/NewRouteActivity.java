package com.example.davidperez.theapp;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
    private AutoCompleteTextView startPointField;
    private AutoCompleteTextView endPointField;
    private Button saveRoute;
    private DummyRoute route;

    private Map FSUMap;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_route);

        saveRoute = (Button) findViewById(R.id.button_save_route);
        nameField = (EditText) findViewById(R.id.newRouteName);
        startPointField = (AutoCompleteTextView) findViewById(R.id.newRouteStartPoint);
        endPointField = (AutoCompleteTextView) findViewById(R.id.newRouteEndPoint);

        PreferencesActivity.setOrientation(this);

        Button recog = (Button) findViewById(R.id.recogButton);
        /*recog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent recogIntent = new Intent(view.getContext(), SRecognizer.class);
                startActivityForResult(recogIntent, DEFAULT_SPEECH_REQUEST);
            }
        });*/

        // create the FSU map
        FSUMap = new Map("FSUMap");
        MapNode Love = new MapNode("Love Building");
        MapNode herman = new MapNode("Herman Gunter Building");
        MapNode carothers = new MapNode("Carothers Hall");
        MapNode kemper = new MapNode("Kemper Lab");
        MapNode carraway = new MapNode("Carraway Building");
        MapNode bookstore = new MapNode("Florida State University Bookstore");
        MapNode dirac = new MapNode("Dirac Science Library");
        MapNode studentunion = new MapNode("Oglesby Student Union");
        MapNode integrationstatue = new MapNode("Integration Statue");
        MapNode HCB = new MapNode("HCB Classroom Building");



        Love.addNeighbor(herman);
        herman.addNeighbor(Love);

        Love.addNeighbor(carothers);
        carothers.addNeighbor(Love);

        Love.addNeighbor(kemper);
        kemper.addNeighbor(Love);

        carothers.addNeighbor(carraway);
        carraway.addNeighbor(carothers);
        carraway.addNeighbor(bookstore);
        carraway.addNeighbor(herman);
        herman.addNeighbor(carraway);

        carothers.addNeighbor(dirac);
        dirac.addNeighbor(carothers);

        bookstore.addNeighbor(carraway);
        bookstore.addNeighbor(dirac);

        integrationstatue.addNeighbor(studentunion);
        studentunion.addNeighbor(integrationstatue);

        carothers.addNeighbor(kemper);
        kemper.addNeighbor(carothers);

        integrationstatue.addNeighbor(HCB);
        HCB.addNeighbor(integrationstatue);

        FSUMap.addNode(Love);
        FSUMap.addNode(herman);
        FSUMap.addNode(carothers);
        FSUMap.addNode(kemper);
        FSUMap.addNode(carraway);
        FSUMap.addNode(bookstore);
        FSUMap.addNode(dirac);
        FSUMap.addNode(studentunion);
        FSUMap.addNode(integrationstatue);
        FSUMap.addNode(HCB);


        FSUMap.writeMap(getApplicationContext());

        ArrayAdapter<MapNode> adapter = new ArrayAdapter<MapNode>(this,
                android.R.layout.simple_dropdown_item_1line, FSUMap.getNodes());

        startPointField.setAdapter(adapter);
        endPointField.setAdapter(adapter);

        saveRoute.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!(nameField.getText().toString().isEmpty())
                      && !(startPointField.getText().toString().isEmpty())
                      && !(endPointField.getText().toString().isEmpty())
                   )
                {
                    DummyRouteCollection list = new DummyRouteCollection();
                    list = list.readRoutes(getApplicationContext());
                    route = new DummyRoute(nameField.getText().toString(), startPointField.getText().toString(),endPointField.getText().toString());
                    route.printRoute();
                    //route.setName(nameField.getText().toString());
                   // route.setStartPoint(startPointField.getText().toString());
                   // route.setEndPoint(endPointField.getText().toString());
                    list.addRoute(route);
                    list.writeRoutes(getApplicationContext());
                    finish();
                }
            }
        });

    }

    protected void onRestart()
    {
        super.onRestart();
        PreferencesActivity.setOrientation(this);
    }
    protected void onPause(Bundle savedInstanceState)
    {
        PreferencesActivity.setOrientation(this);
    }
    protected void onResume(Bundle savedInstanceState)
    {
        PreferencesActivity.setOrientation(this);
    }
    protected void onStart(Bundle savedInstanceState)
    {
        PreferencesActivity.setOrientation(this);
    }
}
