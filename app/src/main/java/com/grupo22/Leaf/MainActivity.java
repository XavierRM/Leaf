package com.grupo22.Leaf;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.grupo22.Leaf.domain.deck.Deck;
import com.grupo22.Leaf.menu.SessionActivity;
import com.grupo22.Leaf.module.adapter.DecksAdapter;
import com.grupo22.Leaf.module.presenter.DecksPresenter;
import com.grupo22.Leaf.module.presenter.DecksPresenterImp;
import com.grupo22.Leaf.module.presenter.DecksView;
import com.grupo22.Leaf.module.viewmodel.DeckViewModel;
import com.grupo22.Leaf.quizgame.GameActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DecksView {

    RecyclerView mRecycler;
    TextView mEmptyView;

    ProgressDialog progressDialog;

    private DecksAdapter mAdapter;

    private DecksPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecycler = findViewById(R.id.decks_list);
        mEmptyView = findViewById(R.id.artists_empty_list);

        setUpView();

        mPresenter = new DecksPresenterImp(this);
        mPresenter.initFlow();
    }

    private void setUpView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(linearLayoutManager);

        mAdapter = new DecksAdapter();

        mAdapter.setClickListener(new DecksAdapter.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view, int position) {
                //mPresenter.onClickDeck(mAdapter.getItem(position));
                DeckViewModel deckViewModel = mAdapter.getItem(position);

                Deck deck = new Deck(deckViewModel.getId(), deckViewModel.getTitle(), deckViewModel.getQuizzes());

                //Here we would create the intent and pass the deck
                Intent intentShare = new Intent(getBaseContext(), GameActivity.class);
                intentShare.putExtra(GameActivity.DECK_KEY, deck);
                startActivity(intentShare);
                Log.d("_TAG", "The deck selected is the following:\n" + deckViewModel.getTitle() + "\n" + deckViewModel.getId());
            }
        });
    }

    @Override
    public void showDecks(List<DeckViewModel> decks) {
        mAdapter.setItems(decks);
        mEmptyView.setVisibility(View.GONE);
        mRecycler.setVisibility(View.VISIBLE);
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void showEmptyView() {
        mEmptyView.setVisibility(View.VISIBLE);
        mRecycler.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        Toast.makeText(this, R.string.general_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateDeck(DeckViewModel deck, int position) {
        mAdapter.updateItem(deck, position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.registry_menu_item:
                Intent intentSession = new Intent(getApplicationContext(), SessionActivity.class);
                intentSession.setType("text/plain");
                startActivity(intentSession);
                return true;
            case R.id.settings_menu_item:
                Toast.makeText(this, R.string.settings_string, Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setLoadingIndicatorVisibility(boolean show) {
        if (show) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Loading decks");
            progressDialog.setMessage("Wait while loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
        else progressDialog.dismiss();
    }
}