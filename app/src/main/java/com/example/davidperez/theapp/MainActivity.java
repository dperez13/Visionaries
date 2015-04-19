package com.example.davidperez.theapp;

import android.app.Activity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //The following line should not be commented out, or the app will probably just crash a ton
        setContentView(R.layout.activity_main);

        //Here we have some code to switch activities on the click of a button. We may have to refactor later to a better design(i.e. call a function that has a single listener that waits for many signals
        Button newRoute = (Button) findViewById(R.id.newRouteButton);
        newRoute.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), new_route.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button pref = (Button) findViewById(R.id.prefButton);
        pref.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), PreferencesActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
        Button saved = (Button) findViewById(R.id.savedRoutes);
        saved.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), saved_routes.class);
                startActivityForResult(myIntent, 0);
            }

        });
        Button voiceRecognition =  (Button) findViewById(R.id.voiceRecognition);
        voiceRecognition.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.v("Eh?", "Starting Voice Recognition.");
                startVoiceRecognition();
            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK && data != null){
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            for(String result: results)
                Log.v("Picked up", result);

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
    }
    private void stopVoiceService(){
        stopService(new Intent(this, SRecognizer.class));
    }
}
