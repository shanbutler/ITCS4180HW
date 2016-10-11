package com.example.shan.group8_hw05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button submitButton;
    EditText cityEditText;
    EditText stateEditText;
    TextView favoritesTextView;

    // http://api.wunderground.com/api/d7cefb706562c221/q/CA/San_Francisco.json

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitButton = (Button) findViewById(R.id.submitButton);
        cityEditText = (EditText) findViewById(R.id.cityEditText);
        stateEditText = (EditText) findViewById(R.id.stateEditText);
        favoritesTextView = (TextView) findViewById(R.id.favoritesOrNo);

        favoritesTextView.setText("There is no city in your favorites");

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get text
                String city = cityEditText.getText().toString();
                String state = stateEditText.getText().toString();

                if(city != "" || state.length() == 2)
                {
                    Intent intent = new Intent(MainActivity.this, CityWeather.class);
                    intent.putExtra("city", city);
                    intent.putExtra("state", state);
                    // generate url
                    String url = "http://api.wunderground.com/api/d7cefb706562c221/q/"+ state + "/" + city + ".json";
                    intent.putExtra("url", url);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Invalid Field", Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}
