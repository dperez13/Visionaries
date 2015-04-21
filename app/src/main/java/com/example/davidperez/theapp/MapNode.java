package com.example.davidperez.theapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Paige on 4/20/2015.
 */
public class MapNode implements Serializable
{
    // MapNodes have a name
    String locationName;
    // and a list of neighbors
    ArrayList<MapNode> neighbors;

    // MapNode constructor
    MapNode(){
        locationName = "";
        neighbors = new ArrayList<MapNode>();
    }

    MapNode(String str){
        locationName = str;
        neighbors = new ArrayList<MapNode>();
    }

    MapNode(String str, ArrayList<MapNode> n){
        locationName = str;
        neighbors = n;
    }
    public String getNodeName() {return locationName; }
    public ArrayList<MapNode> getNeighbors() { return neighbors; }


    public void addNeighbor(MapNode newNeighbor)
    {
        neighbors.add(newNeighbor);
    }

    public String toString()
    {
        return locationName;
    }
}
