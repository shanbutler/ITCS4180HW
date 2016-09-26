package com.example.shan.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Shannon Butler
 * Akhil Ramlakan
 * HW04
 */

public class AsyncJSONGet extends AsyncTask<String, Void, ArrayList<Question>> {

    // allows access to mainactivity methods
    IData activity;

    public AsyncJSONGet(IData activity){
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected ArrayList<Question> doInBackground(String... params) {
        String urlString = params[0];

        try {
            ArrayList<Question> questions = new ArrayList<>();
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

                JSONObject questionsJSON = new JSONObject(sb.toString());
                JSONArray questionsJSONArray = questionsJSON.getJSONArray("questions");

                for (int i = 0; i < questionsJSONArray.length(); i++) {
                    JSONObject questionJSON = questionsJSONArray.getJSONObject(i);

                    int id = questionJSON.getInt("id");
                    String text = questionJSON.getString("text");
                    String imageUrl = questionJSON.getString("image");
                    int answer = questionJSON.getInt("answer");

                    JSONObject choicesJSON = questionJSON.getJSONObject("choices");
                    JSONArray choiceArray = choicesJSON.getJSONArray("choice");
                    ArrayList<String> choices = new ArrayList<>();

                    for (int j = 0; j < choiceArray.length(); j++) {
                        choices.add(choiceArray.getString(j));
                    }
                    questions.add(new Question(id, text, imageUrl, answer, choices);
                }

                return questions;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Question> result) {
        if (result != null) {
            Log.d("demo", result.toString());
            // after fetching the trivia data, show trivia image and start trivia button

            activity.setupData(result);
            super.onPostExecute(result);
        } else {
            Log.d("demo", "null result");
        }

    }

    // allows access to mainactivity variables and methods
    static public interface IData
    {
        public void setupData(ArrayList<Question> result);
    }
}