package com.example.davidperez.theapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Paige on 4/18/2015.
 */
public class EditRouteActivity extends ActionBarActivity {

    private EditText nameField;
    private EditText startPointField;
    private EditText endPointField;
    private Button saveChanges;
    private Intent data;
    private DummyRoute route;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_route);
        data = getIntent();
        route = (DummyRoute) data.getSerializableExtra("toEdit");
        nameField = (EditText) findViewById(R.id.name);
        startPointField = (EditText) findViewById(R.id.startPoint);
        endPointField = (EditText) findViewById(R.id.endPoint);
        saveChanges = (Button) findViewById(R.id.button_save_changes);

        nameField.setText(route.getName());
        startPointField.setText(route.getStartPoint());
        endPointField.setText(route.getEndPoint());
        saveChanges.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                route.setName(nameField.getText().toString());
                route.setStartPoint(startPointField.getText().toString());
                route.setEndPoint(endPointField.getText().toString());

                data.putExtra("toEdit", route);
                setResult(RESULT_OK, data);
                finish();
            }
        });

    }
}
