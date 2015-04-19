package com.example.davidperez.theapp;

import java.io.Serializable;

public class DummyRoute implements Serializable {
    private String name;
    private String startPoint;
    private String endPoint;

    DummyRoute(String n, String sp, String ep) {
        name = n;
        startPoint = sp;
        endPoint = ep;
    }

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
