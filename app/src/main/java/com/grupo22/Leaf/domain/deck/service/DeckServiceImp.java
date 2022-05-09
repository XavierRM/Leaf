package com.grupo22.Leaf.domain.deck.service;

import com.grupo22.Leaf.data.deck.DeckDatasourceImp;
import com.grupo22.Leaf.domain.deck.Deck;
import com.grupo22.Leaf.domain.deck.datasource.DeckDatasource;

import java.util.List;

public class DeckServiceImp implements DeckService {

    private DeckDatasource mDatasource = new DeckDatasourceImp();

    @Override
    public List<Deck> searchDecks(String textToSearch) {

        return mDatasource.searchDecks(textToSearch);
    }
}
