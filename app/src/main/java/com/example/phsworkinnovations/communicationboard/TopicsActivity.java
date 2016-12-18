package com.example.phsworkinnovations.communicationboard;

import android.annotation.TargetApi;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Locale;

public class TopicsActivity extends AppCompatActivity {
    private TextToSpeech speech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        speech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    speech.setLanguage(Locale.US);
                    //speech.setPitch(0);
                }
            }
        });
    }

    @TargetApi((21))//Plays speech for all speech buttons using their tags
    public void playSpeech(View view){
        if (!speech.isSpeaking())
            speech.speak((String) view.getTag(), TextToSpeech.QUEUE_FLUSH, null, null);
    }
}
