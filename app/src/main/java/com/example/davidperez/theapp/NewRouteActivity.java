package com.example.davidperez.theapp;

import android.content.Intent;
import android.os.Bundle;
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

        Button recog = (Button) findViewById(R.id.recogButton);
        /*recog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent recogIntent = new Intent(view.getContext(), SRecognizer.class);
                startActivityForResult(recogIntent, DEFAULT_SPEECH_REQUEST);
            }
        });*/

        // create the FSU map
        FSUMap = new Map("FSU");
        MapNode node = new MapNode("Love Building");
        MapNode node2 = new MapNode("Herman Gunter Building");
        MapNode node3 = new MapNode("Carothers Hall");
        MapNode node4 = new MapNode("Kemper Lab");

        node.addNeighbor(node2);
        node2.addNeighbor(node);
        node.addNeighbor(node3);
        node3.addNeighbor(node);
        node.addNeighbor(node4);
        node4.addNeighbor(node);



        node3.addNeighbor(node4);
        node4.addNeighbor(node3);

        FSUMap.addNode(node);
        FSUMap.addNode(node2);
        FSUMap.addNode(node3);
        FSUMap.addNode(node4);
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
