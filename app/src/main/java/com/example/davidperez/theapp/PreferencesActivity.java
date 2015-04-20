package com.example.davidperez.theapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by davidperez on 2/17/2015.
 */
public class PreferencesActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_preferences);
        PreferencesActivity.setOrientation(this);
        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

    protected void onRestart()
    {
        super.onRestart();
        PreferencesActivity.setOrientation(this);
    }
    protected void onPause(Bundle savedInstanceState)
    {
        PreferencesActivity.setOrientation(this);
    }
    protected void onResume(Bundle savedInstanceState)
    {
        PreferencesActivity.setOrientation(this);
    }
    protected void onStart(Bundle savedInstanceState)
    {
        PreferencesActivity.setOrientation(this);
    }

    public static void setOrientation(Activity a) {

        int orientation;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(a);

        orientation = Integer.parseInt(sp.getString("prefs_screen_orientation", "0"));
        switch (orientation) {
            case (0): {
                a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
            }
            case (1): {
                a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                break;
            }
            case (2): {
                a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                break;
            }
        }
    }
}

