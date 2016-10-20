package com.example.shan.group8_hw05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/**
 * Created by Shan on 10/19/2016.
 * Group 8 HW 06
 * Shannon Butler
 * Akhil Ramlakan
 */

public class MainActivity extends AppCompatActivity {

    Button searchButton;
    EditText cityEditText;
    EditText countryEditText;
    TextView noCitiesTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchButton = (Button) findViewById(R.id.searchButton);
        cityEditText = (EditText) findViewById(R.id.cityEditText);
        countryEditText = (EditText) findViewById(R.id.countryEditText);
        noCitiesTextView = (TextView) findViewById(R.id.noCitiesTextView);

        noCitiesTextView.setText("There are no cities to display. Search the city from the seach box and save.");

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get text
                String city = cityEditText.getText().toString();
                String country = countryEditText.getText().toString();

                if(city != "" || country != "")
                {
                    Intent intent = new Intent(MainActivity.this, CityWeather.class);
                    intent.putExtra("city", city);
                    intent.putExtra("country", country);
                    // generate url
                    String url = "http://api.openweathermap.org/data/2.5/forecast?q=" + city + "," + country + "&mode=json&appid=56daf9a3463d0a1707e781fadf0cd2c5";
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
