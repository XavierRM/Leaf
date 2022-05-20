package com.grupo22.Leaf.domain.deck.service;

import com.grupo22.Leaf.data.deck.DeckDatasourceImp;
import com.grupo22.Leaf.domain.deck.Deck;
import com.grupo22.Leaf.domain.deck.datasource.DeckDatasource;

import java.util.List;

public class DeckServiceImp implements DeckService {

    private DeckDatasource mDatasource = new DeckDatasourceImp();


    @Override
    public void searchDecks(String textToSearch, OnResultListener<List<Deck>> onResultListener) {
        mDatasource.searchDecks(textToSearch, onResultListener);
    }

    @Override
    public void createDeck(Deck deck) {
        mDatasource.createDeck(deck);
    }

    @Override
    public void updateDeck(Deck deck) {
        mDatasource.updateDeck(deck);
    }
}
