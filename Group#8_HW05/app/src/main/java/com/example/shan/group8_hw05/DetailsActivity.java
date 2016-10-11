package com.example.shan.group8_hw05;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    ImageView weatherImage;
    TextView tempDetail;
    TextView conditionDetail;
    TextView maxTempDetail;
    TextView minTempDetail;
    TextView feelsLikeDetail;
    TextView humidityDetail;
    TextView dewpointDetail;
    TextView pressureDetail;
    TextView cloudsDetail;
    TextView windsDetail;
    Weather weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle extras = getIntent().getExtras();

        if(getIntent().getExtras() != null) {
            weather = (Weather) extras.getSerializable("weather");
        }

        weatherImage = (ImageView) findViewById(R.id.imageDetail);
        tempDetail = (TextView) findViewById(R.id.tempDetail);
        conditionDetail = (TextView) findViewById(R.id.conditionDetail);
        maxTempDetail = (TextView) findViewById(R.id.maxTempDetail);
        minTempDetail = (TextView) findViewById(R.id.minTempDetail);
        feelsLikeDetail = (TextView) findViewById(R.id.feelsDetail);
        humidityDetail = (TextView) findViewById(R.id.humidDetail);
        dewpointDetail = (TextView) findViewById(R.id.dewDetail);
        pressureDetail = (TextView) findViewById(R.id.pressDetail);
        cloudsDetail = (TextView) findViewById(R.id.cloudsDetail);
        windsDetail = (TextView) findViewById(R.id.windDetail);

        new AsyncGetImage(weatherImage, DetailsActivity.this).execute(weather.getIconUrl());
        tempDetail.setText(weather.temperature + "°F");
        conditionDetail.setText(weather.climateType);
        maxTempDetail.setText("Maximum Temperature: " + weather.maximumTemp);
        minTempDetail.setText("Minimum Temperature: " + weather.minimumTemp);
        feelsLikeDetail.setText("Feels Like:    " + weather.feelsLike);
        humidityDetail.setText("Humidity:   " + weather.humidity);
        dewpointDetail.setText("Dewpoint:   "+ weather.dewpoint);
        pressureDetail.setText("Pressure:   " + weather.pressure);
        cloudsDetail.setText("Clouds:   " + weather.clouds);
        windsDetail.setText("Winds:     " + weather.windSpeed + " mph, " + weather.windDirection);


    }
}
