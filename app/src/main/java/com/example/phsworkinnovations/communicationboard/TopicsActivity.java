package com.example.phsworkinnovations.communicationboard;

import android.annotation.TargetApi;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class TopicsActivity extends AppCompatActivity {
    public final static String NEXT_TOPIC = "com.example.phsworkinnovations.communicationboard.TOPIC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
    }

    public void switchToATopic(View view){
        Button button = (Button) findViewById(view.getId());
        Intent toTopic = new Intent(this, SpecificTopicActivity.class);
        toTopic.putExtra(NEXT_TOPIC, button.getText());
        startActivity(toTopic);

    }
}
