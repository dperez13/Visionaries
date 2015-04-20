package com.example.davidperez.theapp;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Paige on 4/16/2015.
 */

public class DummyRouteCollection implements Serializable {
    public List<DummyRoute> routes;

    // constructor
    DummyRouteCollection(){


        routes = new ArrayList<DummyRoute>();
        routes.add(new DummyRoute("Morning", "Home", "Love Bldg"));
        routes.add(new DummyRoute("Evening", "Student Union", "Home"));
        routes.add(new DummyRoute("Lunch", "Love Bldg", "Student Union"));
        routes.add(new DummyRoute("Lunch", "Love Bldg", "Student Union"));
    }

    public void addRoute(DummyRoute route)
    {
        routes.add(route);
    }

    public void writeRoutes(Context c) {
            String filename = c.getResources().getString(R.string.savedRoutesFileName);
            ObjectOutput out = null;

            try {
                out = new ObjectOutputStream(new FileOutputStream(new File(c.getFilesDir(),"")+File.separator+filename));
                out.writeObject(this);
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static DummyRouteCollection readRoutes(Context c){
        ObjectInputStream input;
        String filename = c.getResources().getString(R.string.savedRoutesFileName);
        DummyRouteCollection routeList = new DummyRouteCollection();
        try {
            input = new ObjectInputStream(new FileInputStream(new File(new File(c.getFilesDir(),"")+File.separator+filename)));
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
}