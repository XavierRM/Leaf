package com.grupo22.Leaf.domain.question.conversor;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.grupo22.Leaf.domain.deck.Deck;
import com.grupo22.Leaf.domain.question.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonToQuestionConversor {

    public static Question convert(JSONObject content) throws JSONException {
        List<String> answers = new ArrayList<>();
        JSONArray answersJson = content.getJSONArray("answers");
        for (int i = 0; i<answersJson.length(); i++){
            answers.add(answersJson.getJSONObject(i).getString("value"));
        }
        Question question = new Question(content.getString("question"), answers, content.getInt("rightAnswer"));
        return question;
    }

    public static List<Question> convert(JSONArray content) throws JSONException {
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i< content.length(); i++){
            Question question = convert(content.getJSONObject(i));
            questions.add(question);
        }
        return questions;
    }
}
