package com.example.phsworkinnovations.communicationboard;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Locale;

public class SpecificTopicActivity extends AppCompatActivity {
    private TextToSpeech speech;
    private SharedPreferences sharedPrefs;
    private String topic;

    @Override
    @TargetApi((21))
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_topic);
        Intent intent = getIntent();
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(intent.getStringExtra(TopicsActivity.NEXT_TOPIC));
        topic = (String)title.getText();
        initializeTopics();
        sharedPrefs = this.getSharedPreferences("com.example.phsworkinnovations.communicationboard." + topic.replace(' ','_'),Context.MODE_PRIVATE);
        readAllButtons();
        speech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    speech.setLanguage(Locale.US);
                    //speech.setPitch(0);
                }
            }
        });
        //speech.getVoice()
    }
    @TargetApi((21))//Plays speech for all speech buttons using their tags
    public void playSpeech(View view){
        if (!speech.isSpeaking())
            speech.speak((String) view.getTag(),TextToSpeech.QUEUE_FLUSH, null, null);
    }


    private void writeToButton(int id, String text){
        Button button = (Button) findViewById(id);
        button.setText(text);
        button.setTag(text);
        sharedPrefs.edit().putString(String.valueOf(id), text).apply();
    }
    private void readFromButton(int id){
        Button button = (Button) findViewById(Integer.valueOf(id));
        button.setText(sharedPrefs.getString(String.valueOf(id), "Failed"));
        button.setTag(sharedPrefs.getString(String.valueOf(id), "Failed"));

        if (!button.getText().equals("Failed")){
            button.setVisibility(View.VISIBLE);
        }else{
            button.setVisibility(View.GONE);
        }
    }

    private void readAllButtons(){
        readFromButton(R.id.button0);
        readFromButton(R.id.button1);
        readFromButton(R.id.button2);
        readFromButton(R.id.button3);
        readFromButton(R.id.button4);
        readFromButton(R.id.button5);
        readFromButton(R.id.button6);
        readFromButton(R.id.button7);
        readFromButton(R.id.button8);
        readFromButton(R.id.button9);
    }
    private void initializeTopics(){
        if (topic.equals("Workplace")){
            sharedPrefs = getSharedPreferences("com.example.phsworkinnovations.communicationboard.Workplace", Context.MODE_PRIVATE);
            writeToButton(R.id.button0, "What do you need me to do?");
            writeToButton(R.id.button1, "Could you explain?");
            writeToButton(R.id.button2, "Is this right?");
            writeToButton(R.id.button3, "For how long?");
            writeToButton(R.id.button4, "Where?");
        }else if (topic.equals("Daily Life")){
            sharedPrefs = getSharedPreferences("com.example.phsworkinnovations.communicationboard.Daily_Life", Context.MODE_PRIVATE);
            writeToButton(R.id.button0, "What's your name?");
            writeToButton(R.id.button1, "Pleased to meet you");
        }else if (topic.equals("About Me")){
            sharedPrefs = getSharedPreferences("com.example.phsworkinnovations.communicationboard.About_Me", Context.MODE_PRIVATE);
            writeToButton(R.id.button0, "My name is Michael");
        }
    }

    public void addAnExpression(View view){

    }
}
