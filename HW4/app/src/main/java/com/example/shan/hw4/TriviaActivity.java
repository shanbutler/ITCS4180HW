package com.example.shan.hw4;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TriviaActivity extends AppCompatActivity {

    ArrayList<Question> questionsList = new ArrayList<>();
    int numCorrect;
    TextView countdownTextView;
    RadioGroup radioGroup;

    final static String QUESTIONS_KEY = "questionsList";
    final static String NUMBER_CORRECT = "numberCorrect";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        countdownTextView = (TextView) findViewById(R.id.countdownTextView);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        Bundle extras = getIntent().getExtras();


        if(getIntent().getExtras() != null) {
            if(extras.containsKey(MainActivity.QUESTIONS_KEY)){
                questionsList = (ArrayList<Question>) extras.getSerializable(MainActivity.QUESTIONS_KEY);

            } else{
                Log.d("demo", "nothing passed");
            }
        }

        // create a timer
        final CountDownTimer timer = new CountDownTimer(241000, 1000) {

            public void onTick(long millisUntilFinished) {
                countdownTextView.setText("Time Left: " + String.valueOf(millisUntilFinished / 1000) + " seconds");
            }

            public void onFinish() {
                countdownTextView.setText("Time Has Expired!");
                Intent triviaIntent = new Intent(TriviaActivity.this, Stats.class);
                triviaIntent.putExtra(QUESTIONS_KEY, questionsList);
                triviaIntent.putExtra(NUMBER_CORRECT, numCorrect);
                startActivity(triviaIntent);
                finish();
            }
        };
        timer.start();

        /*
        // update questions list radio group depending on amount of choices
        for(int i = 0; i < questionsList.get(???).getChoices().size(); i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(questionsList.get(???).getChoices().get(i));
            radioButton.setId(i);
            radioGroup.addView(radioButton);
        }*/
    }
}
