package com.example.shan.group8_hw05;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 * Created by Shan on 10/19/2016.
 * Group 8 HW 06
 * Shannon Butler
 * Akhil Ramlakan
 */

public class CityWeather extends AppCompatActivity implements AsyncGetJSON.IData {

    ProgressDialog dialog;
    String city, country, strUrl;
    ArrayList<Weather> weathers;
    TextView currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_weather);

        Bundle extras = getIntent().getExtras();

        if(getIntent().getExtras() != null) {
            strUrl = getIntent().getExtras().getString("url");
            city = getIntent().getExtras().getString("city");
            country = getIntent().getExtras().getString("country");
        }

        currentLocation = (TextView) findViewById(R.id.currentLocTextView);
        currentLocation.setText("Current Location: " + city + ", " + country);

        // set up loading message
        dialog = new ProgressDialog(CityWeather.this);
        dialog.setMessage("Loading Hourly Data");
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show(); // show right away

        new AsyncGetJSON(CityWeather.this).execute(strUrl);


    }

    public void setupData(ArrayList<Weather> result)
    {
        dialog.dismiss();
        weathers = result;

        ListView listview = (ListView) findViewById(R.id.listView);

        WeatherAdapter adapter = new WeatherAdapter(this, R.layout.hourly_data, weathers);
        listview.setAdapter(adapter);
        adapter.setNotifyOnChange((true)); // automatically updates on changes

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                Intent i = new Intent(CityWeather.this, DetailsActivity.class);
                                                i.putExtra("weather", weathers.get(position));
                                                startActivity(i);
                                            }


                                        }
        );


    }
}
