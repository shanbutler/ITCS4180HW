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
import java.util.ArrayList;

/**
 * HW 05
 * Akhil Ramlakan
 * Shannon Butler
 *
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
                //JSONObject arrayJSONObj = weatherJSONObj.getJSONObject("array");
                JSONArray hourlyForecast = weatherJSONObj.getJSONArray("hourly_forecast");

                //JSONObject podcastsJSONObj = new JSONObject(sb.toString());
                //JSONObject feedJSONObj = podcastsJSONObj.getJSONObject("feed");
                //JSONArray entryArray = feedJSONObj.getJSONArray("entry");
                //JSONObject entriesObj = entryArray.getJSONObject(0);

                for (int i = 0; i < hourlyForecast.length(); i++) {

                    String time, temperature, dewpoint, clouds, iconUrl,
                            windSpeed, windDirection, windDirection1, windDirection2, climateType, humidity,
                            feelsLike, maximumTemp, minimumTemp, pressure;

                    JSONObject itemsObj = hourlyForecast.getJSONObject(i);
                    JSONObject timeObj = itemsObj.getJSONObject("FCTTIME");
                    JSONObject temperatureObj = itemsObj.getJSONObject("temp");
                    JSONObject dewpointObj = itemsObj.getJSONObject("dewpoint");
                    JSONObject windSpeedObj = itemsObj.getJSONObject("wspd");
                    JSONObject winddirObj = itemsObj.getJSONObject("wdir");
                    JSONObject feelsLikeObj = itemsObj.getJSONObject("feelslike");
                    JSONObject pressureObj = itemsObj.getJSONObject("mslp");


                    //JSONObject entriesObj = entryArray.getJSONObject(i);
                    //JSONObject titleObj = entriesObj.getJSONObject("title");
                    //JSONObject summaryObj = entriesObj.getJSONObject("summary");
                    //JSONArray imageArray = entriesObj.getJSONArray("im:image");


                    //For each item retrieve the title, summary, release date, the smallest image url as
                    //thumbnail, and largest image url.

                    time = timeObj.getString("pretty");
                    temperature = temperatureObj.getString("english");
                    dewpoint = dewpointObj.getString("english");
                    clouds = itemsObj.getString("condition");
                    iconUrl = itemsObj.getString("icon_url");
                    windSpeed = windSpeedObj.getString("english");
                    windDirection1 = winddirObj.getString("degrees");
                    windDirection2 = winddirObj.getString("dir");
                    windDirection = windDirection1 + "° " + windDirection2;
                    climateType = itemsObj.getString("wx");
                    humidity = itemsObj.getString("humidity");
                    feelsLike = feelsLikeObj.getString("english");
                    pressure = pressureObj.getString("english");

                    // calculate max and min temps
                    if(Integer.parseInt(temperature) >= Integer.parseInt(feelsLike))
                    {
                        maximumTemp = temperature;
                    }
                    else{
                        maximumTemp = feelsLike;
                    }
                    minimumTemp = dewpoint;



                    //title = titleObj.getString("label");
                    //summary = summaryObj.getString("label");

                    // check parsing
                    Log.d("demo", "Time " + time);
                    Log.d("demo", "Condition: " + clouds);
                    Log.d("demo", "Temperature " + temperature);
                    Log.d("demo", "Dewpoint: " + dewpoint);
                    Log.d("demo", "Windspeed: " + windSpeed);
                    Log.d("demo", "Climate Type: " + climateType);
                    Log.d("demo", "Humidity: " + humidity);

                    weathers.add(new Weather(time, temperature, dewpoint, clouds, iconUrl,
                            windSpeed, windDirection, climateType, humidity,
                            feelsLike, maximumTemp, minimumTemp, pressure));


                }


            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
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
