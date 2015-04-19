package com.example.davidperez.theapp;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
}