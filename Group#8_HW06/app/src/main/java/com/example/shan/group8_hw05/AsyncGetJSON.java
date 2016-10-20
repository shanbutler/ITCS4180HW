package com.example.shan.group8_hw05;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Shan on 10/19/2016.
 * Group 8 HW 06
 * Shannon Butler
 * Akhil Ramlakan
 */

public class AsyncGetJSON extends AsyncTask<String, Void, ArrayList<Weather>> {

    ArrayList<Weather> weathers = new ArrayList<>();
    // allows access to mainactivity methods
    IData activity;

    public AsyncGetJSON(IData activity){
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected ArrayList<Weather> doInBackground(String... params) {
        String urlString = params[0];
        ArrayList<Weather> weathers = new ArrayList<>();
        Log.d("demo", urlString);

        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode = con.getResponseCode();

            if (statusCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = reader.readLine();
                while (line != null) {
                    sb.append(line);
                    line = reader.readLine();
                }

                JSONObject weatherJSONObj = new JSONObject((sb.toString()));
                JSONArray hourlyForecast = weatherJSONObj.getJSONArray("list");

                for (int i = 0; i < hourlyForecast.length(); i++) {

                    String time, temperature, dewpoint, clouds, iconUrl,
                            windSpeed, windDirection, wind, condition, humidity,
                            feelsLike, maximumTemp, minimumTemp, pressure;

                    JSONObject itemsObj = hourlyForecast.getJSONObject(i); // starts at array item 0
                    JSONObject mainObj = itemsObj.getJSONObject("main");
                    JSONArray weatherArray = itemsObj.getJSONArray("weather"); // array of size 1
                    JSONObject weatherObj = weatherArray.getJSONObject(0);
                    JSONObject windObj = itemsObj.getJSONObject("wind");



                    time = itemsObj.getString("dt_txt");
                    // get time
                    Date dTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
                    String t = new SimpleDateFormat("H:mm").format(dTime);
                    // get date
                    Date dDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
                    String date = new SimpleDateFormat("MMM dd, yyyy").format(dDate);

                    String finalTime = new SimpleDateFormat("h:mm").format(dTime);

                    temperature = mainObj.getString("temp");
                    minimumTemp = mainObj.getString("temp_min");
                    maximumTemp = mainObj.getString("temp_max");
                    // sea_level; grnd_level; temp_kf
                    humidity = mainObj.getString("humidity");
                    pressure = mainObj.getString("pressure");
                    condition = weatherObj.getString("description");
                    windSpeed = windObj.getString("speed");
                    windDirection = windObj.getString("deg");
                    wind = windSpeed + " mph, " + windDirection + "°";
                    iconUrl = weatherObj.getString("icon"); // gives value like 10n
                    // image url example: http://openweathermap.org/img/w/10n.png
                    iconUrl = "http://openweathermap.org/img/w/" + iconUrl + ".png";

                    // check parsing
                    Log.d("demo", "Final Time " + finalTime);
                    Log.d("demo", "Date: " + date);
                    Log.d("demo", "Temperature " + temperature);
                   // Log.d("demo", "Dewpoint: " + dewpoint);
                    Log.d("demo", "Windspeed: " + windSpeed);
                   // Log.d("demo", "Climate Type: " + condition);
                   // Log.d("demo", "Humidity: " + humidity);



                    weathers.add(new Weather(finalTime, temperature, iconUrl,
                            windSpeed, wind, windDirection, condition, humidity,
                            maximumTemp, minimumTemp, pressure));


                }


            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Log.d("debug", "size=" + weathers.size());

        return weathers;
    }

    @Override
    protected void onPostExecute(ArrayList<Weather> result) {
        if (result != null) {
            activity.setupData(result);
        } else {
            Log.d("demo", "null result");
        }
    }

    // allows access to mainactivity variables and methods
    static public interface IData
    {
        public void setupData(ArrayList<Weather> result);
    }

}
