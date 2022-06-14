package com.grupo22.Leaf.domain.quiz.conversor;

import com.grupo22.Leaf.domain.quiz.Quiz;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonToQuizConversor {

    public static Quiz convert(JSONObject content) throws JSONException {
        List<String> answers = new ArrayList<>();
        JSONArray answersJson = content.getJSONArray("answers");
        for (int i = 0; i<answersJson.length(); i++){
            answers.add(answersJson.getJSONObject(i).getString("value"));
        }
        Quiz question = new Quiz(content.getString("question"), answers, content.getInt("rightAnswer"));
        return question;
    }

    public static List<Quiz> convert(JSONArray content) throws JSONException {
        List<Quiz> questions = new ArrayList<>();
        for (int i = 0; i< content.length(); i++){
            Quiz question = convert(content.getJSONObject(i));
            questions.add(question);
        }
        return questions;
    }
}
