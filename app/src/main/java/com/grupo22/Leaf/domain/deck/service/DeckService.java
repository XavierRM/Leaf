package com.grupo22.Leaf.domain.deck.service;

import com.grupo22.Leaf.domain.deck.Deck;

import java.util.List;

public interface DeckService {

    List<Deck> searchDecks(String textToSearch);

    //updateDeck()
}
