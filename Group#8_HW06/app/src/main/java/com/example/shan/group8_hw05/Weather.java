package com.example.shan.group8_hw05;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Shan on 10/19/2016.
 * Group 8 HW 06
 * Shannon Butler
 * Akhil Ramlakan
 */

public class Weather implements Serializable{

    String time, temperature, dewpoint, clouds, iconUrl, windSpeed, windDirection, condition,
        humidity, feelsLike, maximumTemp, minimumTemp, pressure;

//weathers.add(new Weather(finalTime, temperature, iconUrl,
    //windSpeed, wind, windDirection, condition, humidity,
   // maximumTemp, minimumTemp, pressure));

    public Weather(String time, String temperature, String iconUrl,
                   String windSpeed, String wind, String windDirection, String condition, String humidity,
                   String maximumTemp, String minimumTemp, String pressure) {

        this.time = time.substring(0, time.indexOf('E'));
        this.temperature = temperature;
        this.dewpoint = dewpoint;
        this.clouds = clouds;
        this.iconUrl = iconUrl;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.condition = condition;
        this.humidity = humidity;
        this.feelsLike = feelsLike;
        this.maximumTemp = maximumTemp;
        this.minimumTemp = minimumTemp;
        this.pressure = pressure;
    }
    public String getIconUrl() {
        return iconUrl;
    }
    @Override
    public String toString() {
        return "Time: " + time + " Temperature: " + temperature;
    }
}
