package com.grupo22.Leaf.domain.deck.datasource;

import com.grupo22.Leaf.domain.deck.Deck;

import java.util.List;

public interface DeckDatasource {

    List<Deck> searchDecks(String textToSearch);
}
