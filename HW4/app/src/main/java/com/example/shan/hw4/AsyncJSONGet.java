package com.example.shan.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONException;
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

                Log.d("demo", sb.toString());
                ArrayList<Question> questions = QuestionUtil.QuestionsJSONParser.parseQuestions(sb.toString());
                return questions;
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // catch (JSONException e) {
        catch (JSONException e) {
            // TODO Auto-generated catch block
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