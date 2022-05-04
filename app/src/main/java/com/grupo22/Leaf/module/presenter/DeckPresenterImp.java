package com.grupo22.Leaf.module.presenter;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.grupo22.Leaf.domain.deck.Deck;
import com.grupo22.Leaf.module.viewmodel.DeckViewModel;
import com.grupo22.Leaf.module.viewmodel.DecksViewModelMapper;

import java.util.ArrayList;
import java.util.List;

public class DeckPresenterImp implements DeckPresenter{

    private DeckView deckView;

    private List<DeckViewModel> mDecksViewModels;

    public DeckPresenterImp(DeckView view) {

        deckView = view;
    }

    @Override
    public void initFlow() {

        //Get the decks

    }

    @Override
    public void onClickDeck() {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<DeckViewModel> getDecksViewModel(List<Deck> decks) {

        mDecksViewModels = new DecksViewModelMapper(decks).map();
        return mDecksViewModels;
    }

    private class GetDecksTask extends AsyncTask<String, Void, List<Deck>> {

        protected List<Deck> doInBackground(String... textToSearch) {

            //Here we would return the list of decks
            return new ArrayList<>();
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        protected void onPostExecute(List<Deck> result) {

            mDecksViewModels = getDecksViewModel(result);
            deckView.showDecks(mDecksViewModels);
        }
    }
}
