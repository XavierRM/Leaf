package com.grupo22.Leaf.domain.deck.service;

import com.grupo22.Leaf.domain.deck.Deck;

import java.util.List;

public interface DeckService {

    interface OnResultListener<TResult> {
        void onResult(TResult result);
    }

    void searchDecks(String textToSearch, OnResultListener<List<Deck>> listener);

    String createDeck(Deck deck);

    void updateDeck(Deck deck);

    //updateDeck()
}
