package com.example.davidperez.theapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = "MainActivity";

    private Button newRoute;
    private Button pref;
    private Button saved;
    private Button voiceRecognition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //The following line should not be commented out, or the app will probably just crash a ton
        setContentView(R.layout.activity_main);

       //button initialization. Central click listener implemented below.
        newRoute  = (Button) findViewById(R.id.newRouteButton);
        pref = (Button) findViewById(R.id.prefButton);
        saved = (Button) findViewById(R.id.savedRoutes);
        voiceRecognition =  (Button) findViewById(R.id.voiceRecognition);

        newRoute.setOnClickListener(clickHandler);
        pref.setOnClickListener(clickHandler);
        voiceRecognition.setOnClickListener(clickHandler);
        saved.setOnClickListener(clickHandler);
    }


    /****
     *  This function gets the results of the pushed voice recognition activity, and then
     *  sends a message to the appropriate buttons.
     */


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK && data != null){
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            for(String result: results) {
                Log.v("Picked up", result);
                if(result.equals("preferences")) {
                    //Log.v("AHHHH", "it worked.");
                    pref.callOnClick();
                    break;
                }
                else if(result.equals("new route")){
                    newRoute.callOnClick();
                    break;
                }
                else if(result.equals("saved routes")){
                    saved.callOnClick();
                    break;
                }

            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startVoiceRecognition(){
        startActivityForResult(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 1);
        Log.i(TAG, " End Voice Recognition");
    }

    View.OnClickListener clickHandler = new View.OnClickListener() {

        Intent myIntent;
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.newRouteButton:
                    myIntent = new Intent(view.getContext(), new_route.class);
                    startActivityForResult(myIntent, 0);
                    break;
                case R.id.prefButton:
                    myIntent = new Intent(view.getContext(), PreferencesActivity.class);
                    startActivityForResult(myIntent, 0);
                    break;
                case R.id.savedRoutes:
                    myIntent = new Intent(view.getContext(), SavedRoutesActivity.class);
                    startActivityForResult(myIntent, 0);
                    break;
                case R.id.voiceRecognition:
                    startVoiceRecognition();
                    break;
            }

        }

    };
}
