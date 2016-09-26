package com.example.shan.hw4;

import android.util.Log;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Shannon Butler
 * Akhil Ramlakan
 * HW04
 */
public class QuestionUtil {

        static public class QuestionsJSONParser{

            static String id, text, imageURL;
            static int answer;

             static ArrayList<Question> parseQuestions(String jsonString) throws JSONException{
                 ArrayList<Question> questions = new ArrayList<Question>();
                 JSONObject questionMain = new JSONObject(jsonString);
                 JSONArray questionsJSONArray = questionMain.getJSONArray("questions");
                 //JSONArray questionsJSONArray = new JSONArray(jsonString);
                 for(int i = 0 ; i < questionsJSONArray.length(); i++){
                     JSONObject questionJSONObject = questionsJSONArray.getJSONObject(i);

                     // get choices object and array from within question object
                     JSONObject choicesObj = questionJSONObject.getJSONObject("choices");
                     JSONArray choicesArray = choicesObj.getJSONArray("choice");
                     String[] choices = new String[choicesArray.length()];

                     for(int j = 0; j < choicesArray.length()-1; j++)
                     {
                         choices[j] = choicesArray.getString(j);
                         Log.d("choices", "" + choices[j]);
                     }
                     id = questionJSONObject.getString("id");
                     text = questionJSONObject.getString("text");

                     // if no image url found
                     if(questionJSONObject.has("image"))
                     {
                         imageURL = questionJSONObject.getString("image");
                     }
                     else {imageURL = "no url";}
                     answer = (int) Integer.parseInt(choicesObj.getString("answer"));
                     Question question = new Question(id, text, imageURL, answer);
                     question.setChoices(choices); // set the choices array to questions object
                     questions.add(question);

                 }
                 return questions;
             }
            }
        }



