package com.example.davidperez.theapp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import java.lang.ref.WeakReference;

/**
 *
 * Created by davidperez on 3/29/2015.
 * Supposed to be a service that will pick up voices in the background and then process it, work in progress
 *
 **/
public class SRecognizer extends Service {

    protected AudioManager am;                  //ringer and volume control
    protected SpeechRecognizer sr;              //speechrecognizer object that is standard to android
    protected Intent SpeechRecognizerIntent;
    protected final Messenger theMessenger = new Messenger(new IncomingHandler(this));

    protected boolean isListening;
    protected volatile boolean isCountDownOn;
    private boolean isStreamSolo;

    static final int MSG_RECOGNIZER_START_LISTENING = 1;
    static final int MSG_RECOGNIZER_CANCEL = 2;

    @Override
    public void onCreate() {

        super.onCreate();
        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        sr = SpeechRecognizer.createSpeechRecognizer(this);
        sr.setRecognitionListener(new SpeechRecognitionListener());
        SpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        SpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        SpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,this.getPackageName());
        Log.i("Speech_Recognizer", "Service Created!");
    }

    protected class IncomingHandler extends Handler {
        private WeakReference<SRecognizer> mtarget;

        IncomingHandler(SRecognizer target) {
            mtarget = new WeakReference<SRecognizer>(target);
        }
        @Override
        public void handleMessage(Message msg) {
            final SRecognizer target = mtarget.get();
            switch (msg.what) {

                case MSG_RECOGNIZER_START_LISTENING:

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        // turn off beep sound
                        if (!isStreamSolo) {
                            am.setStreamSolo(AudioManager.STREAM_VOICE_CALL, true);
                            isStreamSolo = true;
                        }
                    }
                    if (!isListening) {
                        target.sr.startListening(target.SpeechRecognizerIntent);
                        target.isListening = true;
                        //Log.d(TAG, "message start listening"); //$NON-NLS-1$
                    }
                    break;

                case MSG_RECOGNIZER_CANCEL:
                    if (isStreamSolo) {
                        am.setStreamSolo(AudioManager.STREAM_VOICE_CALL, false);
                        isStreamSolo = false;
                    }
                    target.sr.cancel();
                    target.isListening = false;
                    //Log.d(TAG, "message canceled recognizer"); //$NON-NLS-1$
                    break;
            }
        }
    }


    /*// Count down timer for Jelly Bean work around
    protected CountDownTimer mNoSpeechCountDown = new CountDownTimer(5000, 5000) {
        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onFinish() {
            isCountDownOn = false;
            Message message = Message.obtain(null, MSG_RECOGNIZER_CANCEL);
            try {
                mServerMessenger.send(message);
                message = Message.obtain(null, MSG_RECOGNIZER_START_LISTENING);
                mServerMessenger.send(message);

            } catch (RemoteException e) {

            }
        }
    };*/

    //Done?
    @Override
    public void onDestroy() {
        super.onDestroy();

        if (isCountDownOn) {
            //NoSpeechCountDown.cancel();
        }
        if (sr != null) {
            sr.destroy();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    protected class SpeechRecognitionListener implements RecognitionListener {
        @Override
        public void onBeginningOfSpeech() {
            // speech input will be processed, so there is no need for count down anymore
            if (isCountDownOn) {
                isCountDownOn = false;
                //mNoSpeechCountDown.cancel();
            }
            //Log.d(TAG, "onBeginingOfSpeech"); //$NON-NLS-1$
        }
        @Override
        public void onBufferReceived(byte[] buffer) {

        }
        @Override
        public void onEndOfSpeech() {
            //Log.d(TAG, "onEndOfSpeech"); //$NON-NLS-1$
        }
        @Override
        public void onError(int error) {
            if (isCountDownOn) {
                isCountDownOn = false;
                ///mNoSpeechCountDown.cancel();
            }
            isListening = false;
            Message message = Message.obtain(null, MSG_RECOGNIZER_START_LISTENING);
            try {
                theMessenger.send(message);
            } catch (RemoteException e) {

            }
        }
        @Override
        public void onEvent(int eventType, Bundle params) {

        }
        @Override
        public void onPartialResults(Bundle partialResults) {

        }
        @Override
        public void onReadyForSpeech(Bundle params) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                isCountDownOn = true;
                //mNoSpeechCountDown.start();

            }
            //Log.d(TAG, "onReadyForSpeech"); //$NON-NLS-1$
        }
        @Override
        public void onResults(Bundle results) {
            //Log.d(TAG, "onResults"); //$NON-NLS-1$
        }
        @Override
        public void onRmsChanged(float rmsdB) {

        }
    }
}

/*public TextView mainTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_recognizer);

        Button spchbtn = (Button) findViewById(R.id.start_recon_button);
        mainTextView = (TextView) findViewById(R.id.main_text_view);

        spchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainTextView.setText("");
                Intent listenerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                startActivityForResult(listenerIntent, 1);
            }
        });

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.v("EH?", "Test "+requestCode);
        Log.v("EH?", "Test "+resultCode);
        if (resultCode == Activity.RESULT_OK && data != null) {
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            Log.v("Hmmm", "DATA " + results.toString());
            for(String result : results) {
                mainTextView.append(result + "\n");
            }
        }
    }
    //@Override
    /*public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
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
    }*/