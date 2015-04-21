package com.example.davidperez.theapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

import com.example.davidperez.theapp.Map;
import com.example.davidperez.theapp.MapNode;

public class DummyRoute implements Serializable {
    private String name;
    private String startPoint;
    private String endPoint;
    //private ArrayList<MapNode> theRoute;
    //private Map theMap = new Map();
    //private ArrayList<MapNode> theMapNodes = new Map().getNodes();


    DummyRoute(){
        name = "";
        startPoint = "";
        endPoint = "";
    }

    DummyRoute(String n, String sp, String ep) {
        name = n;
        startPoint = sp;
        endPoint = ep;
        //theRoute = generateRoute();
    }

    //NOT WORKING YET
   /* private ArrayList<MapNode> generateRoute() {
        ArrayList<MapNode> ret = new ArrayList<MapNode>();
        Queue<MapNode> queue = new LinkedList<MapNode>();
        ArrayList<String> visited = new ArrayList<String>();
        //now we build the route here, hopefully.
        MapNode begin = theMap.findNode(startPoint);
        queue.add(begin);
        visited.add(begin.getNodeName());
        boolean done = false;
        while (!queue.isEmpty() && !done) {
            MapNode temp = queue.remove();
            for (MapNode n : temp.getNeighbors()) {
                if (n.getNodeName().equals(endPoint)) {
                    done = true;  //Trying to empty the queue, need to check this out.
                    break;  //basically we are done because we have found the endpoint
                } else {
                    String itr [] = visited.toArray(new String[visited.size()]);
                    for(int i = 0; i < itr.length;++i) {
                        if (!n.getNodeName().equals(itr[i])) {
                            queue.add(n);
                            System.out.println(itr[i]);
                            visited.add(itr[i]);
                        }
                    }
                }

            }
        }
        ListIterator<String> itr2 = visited.listIterator();
        while(itr2.hasNext()) {
            String s  = itr2.next();
            MapNode m = theMap.findNode(s);
            ret.add(m);
        }
        ret.add(theMap.findNode(endPoint));
        return ret;
    }*/

    /*public void printRoute() {
        for(MapNode m: theRoute){
            System.out.println(m.getNodeName());
        }
    }*/
    /*public ArrayList<MapNode> getRoute(){
        return theRoute;
    }*/
    public void setName (String newName){
        name = newName;
    }

    public void setStartPoint (String newStartPoint){
        startPoint = newStartPoint;
    }

    public void setEndPoint (String newEndPoint){
        endPoint = newEndPoint;
    }

    public String toString(){
        return name;
    }

    public String getName() {
        return name;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }
}
