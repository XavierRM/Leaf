package com.grupo22.Leaf.module.viewmodel;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.grupo22.Leaf.domain.deck.Deck;

import java.util.ArrayList;
import java.util.List;

public class DecksViewModelMapper {

    private List<Deck> mDecks;

    public DecksViewModelMapper(List<Deck> artists) {

        mDecks = artists;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<DeckViewModel> map() {

        List<DeckViewModel> decks = new ArrayList<>();
        for (Deck deck : mDecks) {
            decks.add(new DeckViewModel(deck.getId(), deck.getTitle()));
        }
        return decks;
    }
}
