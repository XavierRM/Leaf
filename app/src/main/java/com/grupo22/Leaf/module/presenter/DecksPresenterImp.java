package com.grupo22.Leaf.module.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.grupo22.Leaf.R;
import com.grupo22.Leaf.domain.deck.Deck;

import com.grupo22.Leaf.domain.question.Quiz;
import com.grupo22.Leaf.module.viewmodel.DeckViewModel;
import com.grupo22.Leaf.module.viewmodel.DecksViewModelMapper;

import java.util.ArrayList;
import java.util.List;

public class DecksPresenterImp implements DecksPresenter {

    private DecksView decksView;

    private List<DeckViewModel> mDecksViewModels;

    public DecksPresenterImp(DecksView view) {

        decksView = view;
    }

    @Override
    public void initFlow() {

        new GetDecksTask().execute();

    }

    @Override
    public void onClickDeck(DeckViewModel deck) {
        Toast.makeText((Context) decksView, R.string.general_error, Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<DeckViewModel> getDecksViewModel(List<Deck> decks) {

        mDecksViewModels = new DecksViewModelMapper(decks).map();
        return mDecksViewModels;
    }

    private class GetDecksTask extends AsyncTask<String, Void, List<Deck>> {

        @RequiresApi(api = Build.VERSION_CODES.O)
        protected List<Deck> doInBackground(String... textToSearch) {

            List<Deck> decks = new ArrayList<>();
            List<String> answers = new ArrayList<>();

            answers.add("answer 1");
            answers.add("answer 2");
            answers.add("answer 3");
            answers.add("answer 4");

            Quiz question = new Quiz("Question name?", answers, 4);

            List<Quiz> questions = new ArrayList<>();
            questions.add(question);

            for(int i = 0; i < 100; i++) {
                decks.add(new Deck(Integer.toString(i), (((Context) decksView).getApplicationContext().getString(R.string.decks_number) + " " + i), questions));
            }

            return decks;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        protected void onPostExecute(List<Deck> result) {

            mDecksViewModels = getDecksViewModel(result);
            decksView.showDecks(mDecksViewModels);
        }
    }
}
