package com.example.davidperez.theapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import java.io.File;


/**
 * Created by davidperez on 2/17/2015.
 */
public class saved_routes extends ActionBarActivity {

    //String savedRoutesfile = "savedRoutes.txt";

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_routes);
        // File file = new File(Context().getFilesDir(), savedRoutesfile);
    }
}

