package com.example.davidperez.theapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

/**
 * Created by Paige on 4/18/2015.
 */
public class ViewRouteActivity extends ActionBarActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_route);
        Intent data = getIntent();
        DummyRoute route = (DummyRoute) data.getSerializableExtra("toEdit");
        TextView textViewName = (TextView) findViewById(R.id.name);
        TextView textViewStartPoint = (TextView) findViewById(R.id.startPoint);
        TextView textViewEndPoint = (TextView) findViewById(R.id.endPoint);

        textViewName.setText(route.getName());
        textViewStartPoint.setText(route.getStartPoint());
        textViewEndPoint.setText(route.getEndPoint());

        // TODO: Create an edit button for the route; let it lead to an EditRouteActivity.

        setResult(56);
    }
}
