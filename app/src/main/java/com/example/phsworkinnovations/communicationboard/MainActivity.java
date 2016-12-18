package com.example.phsworkinnovations.communicationboard;

import android.annotation.TargetApi;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.content.Intent;
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
                    //speech.setPitch(0);
                }
            }
        });
    }
    @TargetApi((21))//Plays speech for all speech buttons using their tags
    public void playSpeech(View view){
        if (!speech.isSpeaking())
            speech.speak((String) view.getTag(),TextToSpeech.QUEUE_FLUSH, null, null);
    }

    public void switchToCommonExpressions(View view){
        Intent toCommonExpressions = new Intent(this, CommonExpressionsActivity.class);
        startActivity(toCommonExpressions);
    }
    public void switchToTopics(View view){
        Intent toTopics = new Intent(this, TopicsActivity.class);
        startActivity(toTopics);
    }
}
