package com.grupo22.Leaf.decksmain.presenter;

import com.grupo22.Leaf.decksmain.viewmodel.DeckViewModel;

import java.util.List;

public interface DecksView {

    void showDecks(List<DeckViewModel> decks);

    void showEmptyView();

    void showError();

    void updateDeck(DeckViewModel deck, int position);

    void setLoadingIndicatorVisibility(boolean show);

    String getTitleValue();
}
