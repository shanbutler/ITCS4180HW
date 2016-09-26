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
    TextView questionTextView;
    TextView questionNumView;
    RadioGroup radioGroup;
    ImageView imageView;
    Button nextButton;
    Button exitButton;

    int numCorrect = 0;
    int answer;
    int questionIndex = 0;

    final static String QUESTIONS_KEY = "questionsList";
    final static String TOTAL_QUESTIONS = "totalQuestions";
    final static String NUMBER_CORRECT = "numberCorrect";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        countdownTextView = (TextView) findViewById(R.id.countdownTextView);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        imageView = (ImageView) findViewById(R.id.questionImageView);
        nextButton = (Button) findViewById(R.id.nextButton);
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        questionNumView = (TextView) findViewById(R.id.questionNumTextView);
        exitButton = (Button) findViewById(R.id.quitButton);

        Bundle extras = getIntent().getExtras();

        if(getIntent().getExtras() != null) {
            if(extras.containsKey(MainActivity.QUESTIONS_KEY)){
                questionsList = (ArrayList<Question>) extras.getSerializable(MainActivity.QUESTIONS_KEY);
            } else {
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
                triviaIntent.putExtra(TOTAL_QUESTIONS, questionsList.size());
                triviaIntent.putExtra(NUMBER_CORRECT, numCorrect);
                startActivity(triviaIntent);
                finish();
            }
        };

        questionIndex = questionsList.size() - 2;

        displayQuestion(questionsList.get(questionIndex));

        timer.start();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionIndex < questionsList.size() - 1) {
                    if (radioGroup.getCheckedRadioButtonId() == answer) {
                        numCorrect++;
                    }
                    displayQuestion(questionsList.get(++questionIndex));
                } else {
                    Intent triviaIntent = new Intent(TriviaActivity.this, Stats.class);
                    triviaIntent.putExtra(TOTAL_QUESTIONS, questionsList.size());
                    triviaIntent.putExtra(NUMBER_CORRECT, numCorrect);
                    startActivity(triviaIntent);
                }
            }
        });

        // from MainActivity, exit to home screen on Exit Button press
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    public void displayQuestion (Question question) {
        if (question.getImageURL() != null) {
            new DownloadImage(imageView, this).execute(question.getImageURL());
        } else {
            imageView.setImageBitmap(null);
        }

        questionNumView.setText("Q" + question.getId());

        questionTextView.setText(question.getText());

        radioGroup.removeAllViews();

        for (int i = 0; i < question.getChoices().size(); i++) {
            RadioButton r = new RadioButton(this);
            r.setText(question.getChoices().get(i));
            r.setId(i + 1);

            radioGroup.addView(r);
        }

        answer = question.getAnswer();
    }
}
