package com.example.davidperez.theapp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Vibrator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = "MainActivity";
    private static final int VR_REQUEST_CODE = 0;
    private static final int TTS_REQUEST_CODE = 1;
    private static String messages[] = {"Voice Recognition", "Narrate Options", "Preferences", "Saved Routes", "New Route"};

    //boolean use to loop the prompt
    private TextToSpeech tts;
    private Button newRoute;
    private Button pref;
    private Button saved;
    private Button voiceRecognition;
    private Button narrateOptions;
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
        narrateOptions = (Button) findViewById(R.id.textToSpeech);

        newRoute.setOnClickListener(clickHandler);
        pref.setOnClickListener(clickHandler);
        voiceRecognition.setOnClickListener(clickHandler);
        saved.setOnClickListener(clickHandler);
        narrateOptions.setOnClickListener(clickHandler);


    }

    /****
     *  This function gets the results of the pushed voice recognition activity, and then
     *  sends a message to the appropriate buttons.
     */


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean matched = false;
        //Log.v(TAG,"In onActivityResult.");
        if(requestCode == VR_REQUEST_CODE) {
            //System.out.println(resultCode);
            if (resultCode == Activity.RESULT_OK && data != null) {
                ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                for (String result : results) {
                    //Log.v("Picked up", result);
                    if (result.equals("preferences")) {
                        //Log.v(TAG, "Preferences said.");
                        matched = true;
                        pref.callOnClick();
                        break;
                    } else if (result.equals("new route")) {
                        matched = true;
                        newRoute.callOnClick();
                        break;
                    } else if (result.equals("saved routes")) {
                        matched = true;
                        saved.callOnClick();
                        break;
                    }
                    else if (result.equals("narrate options")) {
                        matched = true;
                        narrateOptions.callOnClick();
                        break;
                    }
                    else if(result.equals("yes")) {
                        narrateOptions.callOnClick();
                        matched = true;
                        break;
                    }
                    else if(result.equals("no")){
                        matched = true;
                    }
                }
                if(!matched) {
                    //Log.i(TAG,"Inside the if.");
                    tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            Log.i(TAG,"Inside the init.");
                            if (status != TextToSpeech.ERROR) {
                                tts.setLanguage(Locale.US);
                                String tryagain = "I'm sorry, that was invalid input. Please try again.";
                                tts.speak(tryagain, TextToSpeech.QUEUE_FLUSH, null);

                                }
                                }
                            });
                        }
                }

         }
        else if(requestCode == TTS_REQUEST_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        HashMap<String, String> map = new HashMap<String, String>();

                        if (status != TextToSpeech.ERROR) {
                            tts.setLanguage(Locale.UK);  //Implement options in the option menu to change this language depending on what the user wants. It is hardcoded to English right now
                            //Over here we can have the text be read dynamically, for now it will just be hard coded.
                            //boolean repeatPrompt = true;
                            String cont = "Would you like to hear the options once more?";
                            //if (repeatPrompt == true){
                                for (String message : messages) {
                                    tts.speak(message, TextToSpeech.QUEUE_ADD, null);
                                }
                                map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "Almost_Done");
                                tts.speak(cont, TextToSpeech.QUEUE_ADD,map);
                                //Looping this will be somewhat difficult...
                            //}

                        }
                    }
                });
                tts.setOnUtteranceCompletedListener(new utterancePListener());

            }
            else {
                // missing data, install it
                Intent installIntent = new Intent();
                installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
            }
        }
    }
    //will have to migrate and start refactoring if this works at some point
    private class utterancePListener extends UtteranceProgressListener implements TextToSpeech.OnUtteranceCompletedListener {

        @Override
        public void onStart(String utteranceId) {
        }
        @Override
        public void onDone(String utteranceId) {
        }
        @Override
        public void onError(String utteranceId) {

        }

        @Override
        public void onUtteranceCompleted(String utteranceId) {
            startVoiceRecognition();
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
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        v.vibrate(500);
        startActivityForResult(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), VR_REQUEST_CODE);
        //Log.i(TAG, " End Voice Recognition");
    }
    private void callTextToSpeech(){
        Intent ttsIntent = new Intent(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(ttsIntent,TTS_REQUEST_CODE);
    }

    View.OnClickListener clickHandler = new View.OnClickListener() {

        Intent myIntent;
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.newRouteButton:
                    myIntent = new Intent(view.getContext(), NewRouteActivity.class);
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
                case R.id.textToSpeech:
                        callTextToSpeech();
                    break;
            }

        }

    };
}
