package com.grupo22.Leaf.decksmain.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.google.firebase.auth.FirebaseAuth;
import com.grupo22.Leaf.MainActivity;
import com.grupo22.Leaf.R;
import com.grupo22.Leaf.domain.deck.Deck;

import com.grupo22.Leaf.domain.deck.service.DeckService;
import com.grupo22.Leaf.domain.deck.service.DeckServiceImp;
import com.grupo22.Leaf.decksmain.viewmodel.DeckViewModel;
import com.grupo22.Leaf.decksmain.viewmodel.DecksViewModelMapper;
import com.grupo22.Leaf.domain.quiz.Quiz;
import com.grupo22.Leaf.domain.user.service.UserService;
import com.grupo22.Leaf.domain.user.service.UserServiceImp;
import com.grupo22.Leaf.edit.ListQuizActivity;

import java.util.ArrayList;
import java.util.List;

public class DecksPresenterImp implements DecksPresenter {

    private DecksView decksView;

    private List<DeckViewModel> mDecksViewModels;

    private DeckService mDeckService = new DeckServiceImp();

    private UserService mUserService = new UserServiceImp();

    String uid = FirebaseAuth.getInstance().getUid();

    private boolean onlyUserDecks = true;

    public DecksPresenterImp(DecksView view) {

        decksView = view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<DeckViewModel> getDecksViewModel(List<Deck> decks) {

        mDecksViewModels = new DecksViewModelMapper(decks).map();
        return mDecksViewModels;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void initFlow() {
        // Añadir onError

        decksView.setLoadingIndicatorVisibility(true);

        Log.d("_TAG",uid);

        String titleSearch = decksView.getTitleValue();
        String category = decksView.getCategory();

        if(onlyUserDecks) {
            mUserService.getUser(uid, user -> {
                mDeckService.searchDecks("", decks -> {
                    //Temporal
                    List<Deck> newDecks = new ArrayList<Deck>();
                    for (Deck d : decks) {
                        if (user.getUserDecks().contains(d.getId()) && d.getTitle().contains(titleSearch) && (category.equals("None") || category.equals(d.getCategory()))) {
                            newDecks.add(d);
                        }
                    }
                    decksView.setLoadingIndicatorVisibility(false);
                    mDecksViewModels = getDecksViewModel(newDecks);
                    decksView.showDecks(mDecksViewModels);
                });
            });
        }else{
            mDeckService.searchDecks("", decks -> {
                List<Deck> newDecks = new ArrayList<Deck>();
                for (Deck d : decks) {
                    if (d.getTitle().contains(titleSearch) && (category.equals("None") || category.equals(d.getCategory()))) {
                        newDecks.add(d);
                    }
                }
                decksView.setLoadingIndicatorVisibility(false);
                mDecksViewModels = getDecksViewModel(newDecks);
                decksView.showDecks(mDecksViewModels);
            });
        }
    }

    @Override
    public void onClickDeck(DeckViewModel deck) {
        Toast.makeText((Context) decksView, R.string.general_error, Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClickCreate(){
        Deck emptyDeck = new Deck("default","none","es",new ArrayList<Quiz>());
        String key = mDeckService.createDeck(emptyDeck);
        emptyDeck.setId(key);

        mUserService.getUser(uid, user->{
            List<String> decks= user.getUserDecks();
            decks.add(key);
            user.setUserDecks(decks);
            mUserService.updateUser(user);
        });

        Intent intentShare = new Intent((Context) decksView, ListQuizActivity.class);
        intentShare.putExtra(((Context) decksView).getString(R.string.DECK_KEY), emptyDeck);
        ((Context) decksView).startActivity(intentShare);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void switchDecksChanged() {
        this.onlyUserDecks=!this.onlyUserDecks;
        initFlow();
    }



/*    private class GetDecksTask extends AsyncTask<String, Void, List<Deck>> {

        @RequiresApi(api = Build.VERSION_CODES.O)
        protected List<Deck> doInBackground(String... textToSearch) {

            List<Deck> decks = new ArrayList<>();
            List<String> answers = new ArrayList<>();

            answers.add("answer 1");
            answers.add("answer 2");
            answers.add("answer 3");
            answers.add("answer 4");

            Quiz question = new Quiz("Question name?", answers, 3);

            List<Quiz> questions = new ArrayList<>();
            questions.add(question);

            for(int i = 0; i < 100; i++) {
                decks.add(new Deck(Integer.toString(i), (((Context) decksView).getApplicationContext().getString(R.string.decks_number) + " " + i), questions));
            }

           // return decks;
            return mDeckService.searchDecks("", new DeckService.OnResultListener<List<Deck>>() {
                @Override
                public void onResult(List<Deck> result) {
                    mDecksViewModels = getDecksViewModel(result);
                    decksView.showDecks(mDecksViewModels);
                }
            });
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        protected void onPostExecute(List<Deck> result) {

            mDecksViewModels = getDecksViewModel(result);
            decksView.showDecks(mDecksViewModels);
        }
    }*/
}
