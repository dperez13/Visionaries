package com.example.davidperez.theapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


/**
 * Created by davidperez on 2/17/2015.
 */
public class SavedRoutesActivity extends ActionBarActivity {

    private DummyRouteCollection routesArray;
    private int index;
    private ListView listView;
    private ArrayAdapter<DummyRoute> adapter;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_routes);

        PreferencesActivity.setOrientation(this);

        routesArray = new DummyRouteCollection();
        //routesArray.writeRoutes(getApplicationContext());
        routesArray = routesArray.readRoutes(getApplicationContext());

        listView = (ListView) findViewById(R.id.listView1);
        // the list becomes filled with this code, based on the ArrayList in DummyRouteCollection
       adapter = new ArrayAdapter<DummyRoute>(this,
                android.R.layout.simple_list_item_1, routesArray.routes);
        // when there is no data in the list, text will display saying so
       TextView empty = (TextView) findViewById(R.id.empty);
       listView.setEmptyView(empty);
       listView.setAdapter(adapter);
       listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
//                DummyRoute item = (DummyRoute) parent.getItemAtPosition(position);

                Intent viewItem = new Intent(SavedRoutesActivity.this, ViewRouteActivity.class);
                viewItem.putExtra("indexString", "" + position);
                viewItem.putExtra("toView", routesArray);
                index = position;
                startActivityForResult(viewItem, 56);
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
        super.onPause();
        PreferencesActivity.setOrientation(this);
        routesArray.writeRoutes(getApplicationContext());

    }
    protected void onResume(Bundle savedInstanceState)
    {
        PreferencesActivity.setOrientation(this);
    }
    protected void onStart(Bundle savedInstanceState)
    {
        PreferencesActivity.setOrientation(this);
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == 56) {
            if (resultCode == RESULT_OK) {
                boolean edited = data.getBooleanExtra("edited", false);
                routesArray = routesArray.readRoutes(getApplicationContext());

                adapter.notifyDataSetChanged();
                recreate();
            }
        }
    }







}

