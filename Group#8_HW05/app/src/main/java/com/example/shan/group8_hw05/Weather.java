package com.example.shan.group8_hw05;

/**
 * HW 05
 * Akhil Ramlakan
 * Shannon Butler
 * Create a weather class containing the following string variables: time, temperature,
 dewpoint, clouds, iconUrl, windSpeed, windDirection, climateType, humidity,
 feelsLike, maximumTemp, minimumTemp and pressure.
 */
public class Weather {

    String time, temperature, dewpoint, clouds, iconUrl, windSpeed, windDirection, climateType,
        humidity, feelsLike, maximumTemp, minimumTemp, pressure;



    public Weather(String time, String temperature, String dewpoint, String clouds, String iconUrl,
                   String windSpeed, String windDirection, String climateType, String humidity,
                   String feelsLike, String maximumTemp, String minimumTemp, String pressure) {

        this.time = time;
        this.temperature = temperature;
        this.dewpoint = dewpoint;
        this.clouds = clouds;
        this.iconUrl = iconUrl;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.climateType = climateType;
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