package com.example.phsworkinnovations.communicationboard;

import android.annotation.TargetApi;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;

import java.util.Locale;

import static java.util.Locale.*;

public class MainActivity extends AppCompatActivity {
    private TextToSpeech speech;
    @Override
    @TargetApi((21))
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        speech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    speech.setLanguage(Locale.US);
                }
            }
        });
    }
    @TargetApi((21))
    public void playYes(View view){
        speech.speak("Yes",TextToSpeech.QUEUE_FLUSH, null, null);
    }

    @TargetApi((21))
    public void playNo(View view){
        speech.speak("No",TextToSpeech.QUEUE_FLUSH, null, null);
    }
}
