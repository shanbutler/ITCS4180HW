package com.example.shan.hw4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Shannon Butler
 * Akhil Ramlakan
 * HW04
 */

public class MainActivity extends AppCompatActivity implements AsyncJSONGet.IData {

    ArrayList<Question> questionsList;
    final static String QUESTIONS_KEY = "questionsList";
    Button exitButton;
    Button startTriviaButton;
    ImageView triviaImage;
    TextView triviaReady;
    ProgressDialog dialog;
    String strUrl = "http://dev.theappsdr.com/apis/trivia_json/index.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set up loading message
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Loading trivia...");
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show(); // show right away

        exitButton = (Button) findViewById(R.id.exitButton);
        startTriviaButton = (Button) findViewById(R.id.startTriviaButton);
        triviaImage = (ImageView) findViewById(R.id.triviaImage); // should be set to invisible at start
        triviaReady = (TextView) findViewById(R.id.triviaReadyTextView); // should be set to "Loading Trivia" to start

        new AsyncJSONGet(MainActivity.this).execute(strUrl);

        startTriviaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass the array list of question objects
                Intent triviaIntent = new Intent(MainActivity.this, TriviaActivity.class);
                triviaIntent.putExtra(QUESTIONS_KEY, questionsList);
                startActivity(triviaIntent);
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

    // do stuff in AsyncTask post execute
    @Override
    public void setupData(ArrayList<Question> result)
    {
        dialog.dismiss();
        triviaImage.setVisibility(View.VISIBLE); // show Trivia image
        triviaReady.setVisibility(View.VISIBLE); // turn on trivia ready text
        triviaReady.setText("Trivia Ready"); // say that trivia is ready
        startTriviaButton.setEnabled(true); // turn on start trivia button
        questionsList = result; // used stored questions for bundling later
    }

}
