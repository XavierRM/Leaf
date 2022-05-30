package com.grupo22.Leaf.decksmain.presenter;

import com.grupo22.Leaf.decksmain.viewmodel.DeckViewModel;

public interface DecksPresenter {

    void initFlow();

    void onClickDeck(DeckViewModel deck);
}
