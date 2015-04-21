package com.example.davidperez.theapp;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Paige on 4/18/2015.
 */
public class EditRouteActivity extends ActionBarActivity {

    private EditText nameField;
    private AutoCompleteTextView startPointField;
    private AutoCompleteTextView endPointField;
    private DummyRouteCollection routeArray;
    private Button saveChanges;
    private Button deleteRoute;
    private Intent data;
    private DummyRoute route;
    private int index;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_route);
        PreferencesActivity.setOrientation(this);

        data = getIntent();
        index = Integer.parseInt(data.getStringExtra("indexString"));
        routeArray = (DummyRouteCollection) data.getSerializableExtra("toEdit");
        route = routeArray.routes.get(index);
        nameField = (EditText) findViewById(R.id.nameEdit);
        startPointField = (AutoCompleteTextView) findViewById(R.id.startPointEdit);
        endPointField = (AutoCompleteTextView) findViewById(R.id.endPointEdit);
        saveChanges = (Button) findViewById(R.id.button_save_changes);

        deleteRoute = (Button) findViewById(R.id.button_delete_route);

        nameField.setText(route.getName());
        startPointField.setText(route.getStartPoint());
        endPointField.setText(route.getEndPoint());

        Map FSUMap = Map.readMap("FSUMap", getApplicationContext());

        ArrayAdapter<MapNode> adapter = new ArrayAdapter<MapNode>(this,
                android.R.layout.simple_dropdown_item_1line, FSUMap.getNodes());

        startPointField.setAdapter(adapter);
        endPointField.setAdapter(adapter);


        saveChanges.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                route.setName(nameField.getText().toString());
                route.setStartPoint(startPointField.getText().toString());
                route.setEndPoint(endPointField.getText().toString());
                routeArray.routes.set(index,route);
                routeArray.writeRoutes(getApplicationContext());
                data.putExtra("delete", false);
                data.putExtra("toEdit", routeArray);
                setResult(RESULT_OK, data);
                finish();
            }
        });

        deleteRoute.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                routeArray.routes.remove(index);
                routeArray.writeRoutes(getApplicationContext());
                data.putExtra("delete", true);
                data.putExtra("toEdit", routeArray);
                setResult(RESULT_OK, data);
                finish();
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
