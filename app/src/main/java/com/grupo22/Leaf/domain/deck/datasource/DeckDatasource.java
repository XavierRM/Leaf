package com.grupo22.Leaf.domain.deck.datasource;

import com.grupo22.Leaf.domain.deck.Deck;
import com.grupo22.Leaf.domain.deck.service.DeckService;

import java.util.List;

public interface DeckDatasource {

    List<Deck> searchDecks(String textToSearch, DeckService.OnResultListener<List<Deck>> onResultListener);
}
