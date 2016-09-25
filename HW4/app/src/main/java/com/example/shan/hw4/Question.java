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
public class Question implements Serializable{

        String id, text, imageURL;
        int answer; //answer is in choices array!
        //String> choices = new ArrayList<String>();
        String[] choices;


        // couldn't get this to work so using other constructor
        public Question(JSONObject questionJSONObject) throws JSONException{
            this.id = questionJSONObject.getString("id");
            //this.answer = (int) Integer.parseInt(questionJSONObject.getString("answer")); // answer is in choices array
            this.text = questionJSONObject.getString("text");
            this.imageURL = questionJSONObject.getString("image");

        }

        public Question(String id, String text, String imageURL, int answer)
        {
            this.id = id;
            this.text = text;
            if(imageURL != null)
            {
                this.imageURL = imageURL;
            } else {this.imageURL = " ";}

            this.answer = answer;
        }


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return imageURL;
        }

        public void setImage(String url) {
            this.imageURL = url;
        }

        @Override
        public String toString() {
            return "question [id=" + id + ", text=" + text + ", imageURL=" + imageURL + "]";
        }

        public int getAnswer() {
            return answer;
        }

        public void setAnswer(int answer) {
            this.answer = answer;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        //pblic void addChoice(String s) {
          //  choices.add(s);
        //}//

        //public ArrayList<String> getChoices() {
        //    return choices;
        //}

        public void setChoices(String[] choices) {
            this.choices = choices;
        }

}




