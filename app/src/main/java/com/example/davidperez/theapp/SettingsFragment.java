package com.example.davidperez.theapp;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferencesActivity.setOrientation(getActivity());

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }


    protected void onRestart()
    {
        PreferencesActivity.setOrientation(getActivity());
    }
    protected void onPause(Bundle savedInstanceState)
    {
        PreferencesActivity.setOrientation(getActivity());
    }
    protected void onResume(Bundle savedInstanceState)
    {
        PreferencesActivity.setOrientation(getActivity());
    }
    protected void onStart(Bundle savedInstanceState)
    {
        PreferencesActivity.setOrientation(getActivity());
    }
}
