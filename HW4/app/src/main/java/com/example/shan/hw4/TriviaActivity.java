package com.example.shan.hw4;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TriviaActivity extends AppCompatActivity {

    ArrayList<Question> questionsList;
    TextView countdownTextView;
    RadioGroup radioGroup;
    ImageView imageView;
    Button nextbutton;

    int numCorrect;
    int questionIndex = 0;

    final static String QUESTIONS_KEY = "questionsList";
    final static String NUMBER_CORRECT = "numberCorrect";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        countdownTextView = (TextView) findViewById(R.id.countdownTextView);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        imageView = (ImageView) findViewById(R.id.questionImageView);
        nextbutton = (Button) findViewById(R.id.nextButton);

        int numCorrect = 0;
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
                /*countdownTextView.setText("Time Has Expired!");
                Intent triviaIntent = new Intent(TriviaActivity.this, Stats.class);
                triviaIntent.putExtra(QUESTIONS_KEY, questionsList);
                triviaIntent.putExtra(NUMBER_CORRECT, numCorrect);
                startActivity(triviaIntent);*/
                finish();
            }
        };

        displayQuestion(questionsList.get(questionIndex));

        timer.start();

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionIndex < questionsList.size() - 1)
                    displayQuestion(questionsList.get(++questionIndex));
            }
        });
    }

    public void displayQuestion (Question question) {
        if (question.getImageURL() != null) {
            new DownloadImage(imageView, this).execute(question.getImageURL());
        } else {
            imageView.setImageBitmap(null);
        }

        Log.d("debug", "question=" + question.toString());

        if (question.getChoices() != null) {
            Log.d("debug", "Hurrah! " + question.getId());
        } else {
            Log.d("debug", ":(");
        }

        /*for (int i = 0; i < question.getChoices().size(); i++) {
            RadioButton r = new RadioButton(this);
            String choice = question.getChoices().get(i);

            radioGroup.addView(r, i);
        }*/
    }
}
