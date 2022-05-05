package com.grupo22.Leaf.domain.question.conversor;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.grupo22.Leaf.domain.question.Quiz;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class QuestionToJsonConversor {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static JSONObject convert(Quiz quiz) throws JSONException {
        JSONArray answersArray = new JSONArray();
        for (String s: quiz.getAnswers()){
            answersArray.put(new JSONObject().put("value",s));
        }
        JSONObject questionJSON = new JSONObject().put("question",quiz.getName())
                .put("answers",answersArray)
                .put("rightAnswer",quiz.getRightAnswer());
        return questionJSON;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static JSONArray convert(List<Quiz> questions) throws JSONException {
        JSONArray questionsArray = new JSONArray();
        for (Quiz q : questions){
            questionsArray.put(convert(q));
        }
        return questionsArray;
    }
}
