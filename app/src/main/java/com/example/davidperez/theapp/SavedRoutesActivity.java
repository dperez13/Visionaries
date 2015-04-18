package com.example.davidperez.theapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;


/**
 * Created by davidperez on 2/17/2015.
 */
public class SavedRoutesActivity extends ActionBarActivity {

    private DummyRouteCollection routesArray;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_routes);

        /*
        // for debugging: resets the collection with a full constructor
        routesArray = new DummyRouteCollection();
        writeRoutes(routesArray);*/
        routesArray = readRoutes();

        ListView listView = (ListView) findViewById(R.id.listView1);
        // the list becomes filled with this code, based on the ArrayList in DummyRouteCollection
        ArrayAdapter<DummyRoute> adapter = new ArrayAdapter<DummyRoute>(this,
                android.R.layout.simple_list_item_1, routesArray.routes);
        // when there is no data in the list, text will display saying so
        TextView empty = (TextView) findViewById(R.id.empty);
        listView.setEmptyView(empty);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                DummyRoute item = (DummyRoute) parent.getItemAtPosition(position);

                Intent editItem = new Intent(SavedRoutesActivity.this, ViewRouteActivity.class);
                editItem.putExtra("toEdit",item);
                editItem.putExtra("index", position);
                startActivityForResult(editItem, 56);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == 56) {
            if (resultCode == RESULT_OK) {
                DummyRoute newroute = (DummyRoute)data.getSerializableExtra("toEdit");
                int index = data.getIntExtra("position", 0);

                routesArray.routes.set(index, newroute);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        writeRoutes(routesArray);
    }

    public DummyRouteCollection readRoutes(){
        ObjectInputStream input;
        String filename = getString(R.string.savedRoutesFileName);
        DummyRouteCollection routeList = new DummyRouteCollection();
        try {
            input = new ObjectInputStream(new FileInputStream(new File(new File(getFilesDir(),"")+File.separator+filename)));
            routeList = (DummyRouteCollection) input.readObject();
            input.close();
            return routeList;
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            return new DummyRouteCollection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return routeList;
    }

    public void writeRoutes(DummyRouteCollection routesObject){
        String filename = getString(R.string.savedRoutesFileName);
        ObjectOutput out = null;

        try {
            out = new ObjectOutputStream(new FileOutputStream(new File(getFilesDir(),"")+File.separator+filename));
            out.writeObject(routesObject);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

