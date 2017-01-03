package com.example.phsworkinnovations.communicationboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private SpeechOnClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listener = new SpeechOnClickListener(getApplicationContext());
    }

    public void playSpeech(View view){
        listener.onClick(view);
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
