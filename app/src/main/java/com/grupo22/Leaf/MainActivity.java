package com.grupo22.Leaf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.grupo22.Leaf.module.adapter.DecksAdapter;
import com.grupo22.Leaf.module.presenter.DecksPresenter;
import com.grupo22.Leaf.module.presenter.DecksPresenterImp;
import com.grupo22.Leaf.module.presenter.DecksView;
import com.grupo22.Leaf.module.viewmodel.DeckViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DecksView {

    RecyclerView mRecycler;
    TextView mEmptyView;

    private DecksAdapter mAdapter;

    private DecksPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecycler = findViewById(R.id.artists_list);
        mEmptyView = findViewById(R.id.artists_empty_list);

        setUpView();

        mPresenter = new DecksPresenterImp(this);
        mPresenter.initFlow();
    }

    private void setUpView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(linearLayoutManager);

        mAdapter = new DecksAdapter();
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void showDecks(List<DeckViewModel> decks) {
        mAdapter.setItems(decks);
        mEmptyView.setVisibility(View.GONE);
        mRecycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyView() {
        mEmptyView.setVisibility(View.VISIBLE);
        mRecycler.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        Toast.makeText(this, R.string.error_general, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void updateDeck(DeckViewModel deck, int position) {
        mAdapter.updateItem(deck, position);
    }
}