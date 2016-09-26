package com.example.shan.hw4;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Shannon Butler
 * Akhil Ramlakan
 * HW04
 */
public class Question implements Serializable {
    String text, imageURL;
    int id, answer; //answer is in choices array!
    ArrayList<String>  choices = new ArrayList<String>();

    public Question(int id, String text, String imageURL,
                    int answer, ArrayList<String> choices) {
        this.id = id;
        this.text = text;
        if(imageURL != null)
            this.imageURL = imageURL;
        else
            this.imageURL = " ";
        this.answer = answer;
        this.choices = null;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getImageURL() {
        return imageURL;
    }

    public int getAnswer() {
        return answer;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }
}




