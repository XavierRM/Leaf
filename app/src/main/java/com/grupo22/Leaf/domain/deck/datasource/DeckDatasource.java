package com.grupo22.Leaf.domain.deck.datasource;

import com.grupo22.Leaf.domain.deck.Deck;
import com.grupo22.Leaf.domain.deck.service.DeckService;

import java.util.List;

public interface DeckDatasource {

    void searchDecks(String textToSearch, DeckService.OnResultListener<List<Deck>> onResultListener);

    String createDeck(Deck deck);

    void updateDeck(Deck deck);
}
