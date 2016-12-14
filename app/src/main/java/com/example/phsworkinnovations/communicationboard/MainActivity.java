package com.example.phsworkinnovations.communicationboard;

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
    public void playYes(View view){
        speech.speak("Yes",TextToSpeech.QUEUE_FLUSH, null, null);
    }
}
