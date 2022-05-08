package com.grupo22.Leaf.domain.deck.conversor;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.grupo22.Leaf.domain.deck.Deck;
import com.grupo22.Leaf.domain.quiz.conversor.JsonToQuizConversor;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JsonToDeckConversor {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Deck convert(JSONObject json) throws JSONException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return new Deck(json.getString("title"), json.getString("category"), LocalDate.parse(json.getString("creationDate"), formatter), LocalDate.parse(json.getString("lastUpdate"),formatter), json.getString("lang"), JsonToQuizConversor.convert(json.getJSONArray("content")));
    }
}
