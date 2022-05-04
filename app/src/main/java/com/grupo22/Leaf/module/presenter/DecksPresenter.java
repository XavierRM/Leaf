package com.grupo22.Leaf.module.presenter;

import com.grupo22.Leaf.domain.deck.Deck;
import com.grupo22.Leaf.module.viewmodel.DeckViewModel;

public interface DecksPresenter {

    void initFlow();

    void onClickDeck(DeckViewModel deck);
}
