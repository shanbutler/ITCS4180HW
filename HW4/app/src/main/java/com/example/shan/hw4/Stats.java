package com.example.shan.hw4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Stats extends AppCompatActivity {

    int numCorrect;
    int totalQuestions;
    TextView numCorrectText;
    ProgressBar numCorrectProgress;
    Button exitButton;
    Button tryAgainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        numCorrectText = (TextView) findViewById(R.id.countdownTextView);
        numCorrectProgress = (ProgressBar) findViewById(R.id.correctAnswersProg);
        exitButton = (Button) findViewById(R.id.quitButtonStats);
        tryAgainButton = (Button) findViewById(R.id.tryAgainButton);

        Bundle extras = getIntent().getExtras();

        if(getIntent().getExtras() != null) {
            if(extras.containsKey(MainActivity.QUESTIONS_KEY)){
                numCorrect = (int) extras.getSerializable(TriviaActivity.NUMBER_CORRECT);
                totalQuestions = (int) extras.getSerializable(TriviaActivity.TOTAL_QUESTIONS);

                Log.d("debug", "numCorrect=" + numCorrect + "\ntotalQuestions=" + totalQuestions);
            } else {
                Log.d("demo", "nothing passed");
            }
        }

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

        //numCorrectText.setText(numCorrect + "");
        //numCorrectProgress.setProgress((totalQuestions / numCorrect));
    }
}
