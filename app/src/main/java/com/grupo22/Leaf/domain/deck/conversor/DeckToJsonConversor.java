package com.grupo22.Leaf.domain.deck.conversor;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.grupo22.Leaf.domain.deck.Deck;
import com.grupo22.Leaf.domain.quiz.conversor.QuizToJsonConversor;

import org.json.JSONException;
import org.json.JSONObject;

public class DeckToJsonConversor {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static JSONObject convert(Deck deck) throws JSONException {
        JSONObject deckInfo = new JSONObject().put("title", deck.getTitle())
                .put("category",deck.getCategory())
                .put("creationDate",deck.getCreationDate())
                .put("lastUpdate",deck.getLastUpdate())
                .put("lang",deck.getLang())
                .put("content", QuizToJsonConversor.convert(deck.getQuizzes()));
        JSONObject deckJSON = new JSONObject().put("deck",deckInfo);
        return new JSONObject();
    }
}
