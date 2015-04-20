package com.example.davidperez.theapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by davidperez on 2/17/2015.
 */
public class PreferencesActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_preferences);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}

