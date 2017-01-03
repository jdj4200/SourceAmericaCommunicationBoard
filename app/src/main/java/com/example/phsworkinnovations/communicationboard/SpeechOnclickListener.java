package com.example.phsworkinnovations.communicationboard;
import android.annotation.TargetApi;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class SpeechOnClickListener implements View.OnClickListener {
    private TextToSpeech speech;

    public SpeechOnClickListener(Context context){
        speech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    speech.setLanguage(Locale.US);
                }
            }
        });
    }

    @Override
    @TargetApi((21))//Plays speech for all speech buttons using their tags
    public void onClick(View view) {
        Button button = (Button) view;
        if (!speech.isSpeaking())
            speech.speak((String) button.getText(),TextToSpeech.QUEUE_FLUSH, null, null);
    }
}
