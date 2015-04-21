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
    // maps have a list of nodes
    private ArrayList<MapNode> nodes = new ArrayList<MapNode>();
    private String mapName;

    // MAP constructors. Using the default to create our map for the presentation. Needs changing.
    public Map()
    {
        mapName = "FSU_MAP";
        MapNode Love = new MapNode("Love Building");
        MapNode herman = new MapNode("Herman Gunter Building");
        MapNode carothers = new MapNode("Carothers Hall");
        MapNode kemper = new MapNode("Kemper Lab");
        MapNode carraway = new MapNode("Carraway Building");
        MapNode bookstore = new MapNode("Florida State University Bookstore");
        MapNode dirac = new MapNode("Dirac Science Library");
        MapNode studentunion = new MapNode("Oglesby Student Union");
        MapNode integrationstatue = new MapNode("Integration Statue");
        MapNode HCB = new MapNode("HCB Classroom Building");

        Love.addNeighbor(herman);
        herman.addNeighbor(Love);
        Love.addNeighbor(carothers);
        carothers.addNeighbor(Love);
        Love.addNeighbor(kemper);
        kemper.addNeighbor(Love);
        carothers.addNeighbor(carraway);
        carraway.addNeighbor(carothers);
        carraway.addNeighbor(bookstore);
        carraway.addNeighbor(herman);
        herman.addNeighbor(carraway);
        carothers.addNeighbor(dirac);
        dirac.addNeighbor(carothers);
        bookstore.addNeighbor(carraway);
        bookstore.addNeighbor(dirac);
        integrationstatue.addNeighbor(studentunion);
        studentunion.addNeighbor(integrationstatue);
        carothers.addNeighbor(kemper);
        kemper.addNeighbor(carothers);
        integrationstatue.addNeighbor(HCB);
        HCB.addNeighbor(integrationstatue);

        nodes.add(Love);
        nodes.add(herman);
        nodes.add(carothers);
        nodes.add(kemper);
        nodes.add(carraway);
        nodes.add(bookstore);
        nodes.add(dirac);
        nodes.add(studentunion);
        nodes.add(integrationstatue);
        nodes.add(HCB);
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


    public MapNode findNode(String s) {
        MapNode ret = new MapNode();
        for(MapNode m : nodes) {
            if(m.getNodeName().equals(s))
                ret = m;
        }
        return ret;
    }

    public void addNode (MapNode newNode)
    {
        nodes.add(newNode);
    }

    public ArrayList<MapNode> getNodes() {
        return nodes;
    }
}
