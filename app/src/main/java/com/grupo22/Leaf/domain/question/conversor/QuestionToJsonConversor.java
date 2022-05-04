package com.grupo22.Leaf.domain.question.conversor;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.grupo22.Leaf.domain.question.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class QuestionToJsonConversor {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static JSONObject convert(Question question) throws JSONException {
        JSONArray answersArray = new JSONArray();
        for (String s: question.getAnswers()){
            answersArray.put(new JSONObject().put("value",s));
        }
        JSONObject questionJSON = new JSONObject().put("question",question.getName())
                .put("answers",answersArray)
                .put("rightAnswer",question.getRightAnswer());
        return questionJSON;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static JSONArray convert(List<Question> questions) throws JSONException {
        JSONArray questionsArray = new JSONArray();
        for (Question q : questions){
            questionsArray.put(convert(q));
        }
        return questionsArray;
    }
}
