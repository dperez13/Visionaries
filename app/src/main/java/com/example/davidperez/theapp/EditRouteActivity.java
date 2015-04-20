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
    private Button saveChanges;
    private Button deleteRoute;
    private Intent data;
    private DummyRoute route;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_route);
        data = getIntent();
        route = (DummyRoute) data.getSerializableExtra("toEdit");
        nameField = (EditText) findViewById(R.id.name);
        startPointField = (AutoCompleteTextView) findViewById(R.id.startPoint);
        endPointField = (AutoCompleteTextView) findViewById(R.id.endPoint);
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
                data.putExtra("delete", false);
                data.putExtra("toEdit", route);
                setResult(RESULT_OK, data);
                finish();
            }
        });

        deleteRoute.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                data.putExtra("delete", true);
                data.putExtra("toEdit", route);
                setResult(RESULT_OK, data);
                finish();
            }
        });

    }
}
