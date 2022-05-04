package com.grupo22.Leaf.module.presenter;

import com.grupo22.Leaf.module.viewmodel.DeckViewModel;

import java.util.List;

public interface DeckView {

    void showDecks(List<DeckViewModel> decks);

    void showEmptyView();

    void showError();

    void updateDeck(DeckViewModel deck, int position);
}
