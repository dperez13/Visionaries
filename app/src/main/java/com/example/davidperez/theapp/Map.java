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

/**
 * Created by Paige on 4/20/2015.
 */
public class Map implements Serializable {
    // maps have a list of nodes,
    private ArrayList<MapNode> nodes;
    private String mapName;

    // MAP constructors
    public Map()
    {

    }
    public Map(String str)
    {
        mapName = str;
        nodes = new ArrayList<MapNode>();
    }

    public void writeMap(Context c) {
        String filename = mapName;
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

    public static Map readMap(String mapName, Context c){
        ObjectInputStream input;
        String filename = mapName;
        Map newMap = new Map(filename);
        try {
            input = new ObjectInputStream(new FileInputStream(new File(new File(c.getFilesDir(),"")+File.separator+filename)));
            newMap = (Map) input.readObject();
            input.close();
            return newMap;
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            return new Map();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return newMap;
    }

    public void addNode (MapNode newNode)
    {
        nodes.add(newNode);
    }

    public ArrayList<MapNode> getNodes() {
        return nodes;
    }
}
