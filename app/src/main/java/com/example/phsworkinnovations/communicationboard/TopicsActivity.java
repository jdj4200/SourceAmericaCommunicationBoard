package com.example.phsworkinnovations.communicationboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TopicsActivity extends AppCompatActivity {
    public final static String NEXT_TOPIC = "com.example.phsworkinnovations.communicationboard.TOPIC";
    private SharedPreferences sharedPrefs;
    private LinearLayout buttonList;
    private LinearLayout.LayoutParams layout;
    private RelativeLayout.LayoutParams toTheRight;
    private Button addTopic;
    private boolean isInEditMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        int sp10 = spToPixel(10);
        layout.setMargins(sp10, sp10, sp10, sp10);
        toTheRight = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        toTheRight.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        toTheRight.addRule(RelativeLayout.CENTER_VERTICAL);
        buttonList = (LinearLayout) findViewById(R.id.list);
        addTopic = (Button) findViewById(R.id.addTopic);
        sharedPrefs = getPreferences(Context.MODE_PRIVATE);
        sharedPrefs.edit().clear().apply();
        int i;
        for (i = 0; i < sharedPrefs.getInt("Size", 0); i++) {
            makeButton(sharedPrefs.getString(String.valueOf(i), ""));
        }
        sharedPrefs.edit().putInt("Size", i).apply();
    }

    private int spToPixel(int sp){
        return (int) (sp * getApplicationContext().getResources().getDisplayMetrics().scaledDensity);
    }

    private ViewGroup makeButton(String text) {
        RelativeLayout panel = new RelativeLayout(getApplicationContext());
        panel.setGravity(Gravity.CENTER_HORIZONTAL);
        Button button = new Button(getApplicationContext());
        button.setText(text);
        button.setTextSize(40);
        button.setBackgroundResource(R.color.topicOrange);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SpecificTopicActivity.class);
                intent.putExtra(NEXT_TOPIC, ((Button) view).getText());
                startActivity(intent);
            }
        });
        button.setPadding(0, 0, 0, 0);
        panel.addView(button);
        button.setGravity(Gravity.CENTER_HORIZONTAL);
        EditText edit = new EditText(getApplicationContext());
        edit.setTextSize(40);
        edit.setHint("Topic");
        edit.setBackgroundResource(R.color.topicOrange);
        edit.setImeOptions(EditorInfo.IME_ACTION_DONE);
        edit.setInputType(InputType.TYPE_TEXT_FLAG_IME_MULTI_LINE);
        edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE){
                    hideSoftKeyboard();
                    return true;
                }
                return false;
            }
        });
        edit.setVisibility(View.GONE);
        panel.addView(edit);
        Button delete = new Button(getApplicationContext());
        delete.setTextSize(40);
        delete.setText("X");
        delete.setBackgroundResource(R.color.topicOrange);
        delete.setPadding(0,0,0,0);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteButton((ViewGroup) view.getParent());
            }
        });
        delete.setVisibility(View.GONE);
        panel.addView(delete, toTheRight);
        buttonList.addView(panel,layout);
        return panel;
    }
    private void hideSoftKeyboard() {
        if (getCurrentFocus() != null){
            InputMethodManager inputMethodManager = (InputMethodManager)this.getSystemService(MainActivity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
    }
    private void deleteButton(ViewGroup panel){
        buttonList.removeView((View)panel.getParent());
        int size = sharedPrefs.getInt("Size", 1);
        sharedPrefs.edit().putInt("Size", size - 1).apply();
    }

    public void addATopic(View view){
        ViewGroup panel = makeButton("");
        panel.getChildAt(0).setVisibility(View.GONE);
        panel.getChildAt(1).setVisibility(View.VISIBLE);
        panel.getChildAt(2).setVisibility(View.VISIBLE);
        int size = sharedPrefs.getInt("Size", 0);
        sharedPrefs.edit().putInt("Size", size + 1).apply();
    }
    public void switchEditMode(View view) { //for adding, deleting, and editing buttons
        int i;
        ViewGroup panel;
        if (!isInEditMode) { //To edit
            ((Button) findViewById(R.id.switchEdit)).setText(R.string.topicMode);
            for (i = 0; i <  sharedPrefs.getInt("Size", 0); i++) {
                panel = (ViewGroup) buttonList.getChildAt(i);
                panel.getChildAt(0).setVisibility(View.GONE);
                panel.getChildAt(1).setVisibility(View.VISIBLE);
                panel.getChildAt(2).setVisibility(View.VISIBLE);
            }
            addTopic.setVisibility(View.VISIBLE);
        } else { //To stop editing
            ((Button) findViewById(R.id.switchEdit)).setText(R.string.editMode);
            hideSoftKeyboard();
            for (i = 0; i < sharedPrefs.getInt("Size", 0); i++) {
                panel = (ViewGroup) buttonList.getChildAt(i);
                EditText edit = (EditText) panel.getChildAt(1);
                if (edit.getText().toString().equals("")) {
                    deleteButton(panel);
                    i--;
                } else {
                    Button button = ((Button) panel.getChildAt(0));
                    button.setText(edit.getText().toString());
                    panel.getChildAt(0).setVisibility(View.VISIBLE);
                    edit.setVisibility(View.GONE);
                    panel.getChildAt(2).setVisibility(View.GONE);
                    sharedPrefs.edit().putString(String.valueOf(i), button.getText().toString()).apply();
                }
            }
            sharedPrefs.edit().putInt("Size", i).apply();
            addTopic.setVisibility(View.GONE);
        }
        isInEditMode = !isInEditMode;
    }
}
