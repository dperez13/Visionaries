package com.example.davidperez.theapp;

/**
 * Created by davidperez on 2/25/2015.
 */
public class Route {

    private String name;
    private String routeIDs[];          //identification of route information by names?
    private int routeArray[];           //for now array of integers while we figure out how we will actually store locations
    public Route() {
    }

    public void setRouteIDs(String s[]){
        routeIDs = s;
    }
    public void setName(String n){
        name = n;
    }
    public void setRouteArray(int route[]) {
        routeArray = route;
    }
    public String [] getRouteIDs(){
        return routeIDs;
    }
    public String getName(){
        return name;
    }
    public int[] getRouteArray() {
        return routeArray;
    }

}
