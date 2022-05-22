package com.grupo22.Leaf.decksmain.adapter;

import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.grupo22.Leaf.R;
import com.grupo22.Leaf.decksmain.viewmodel.DeckViewModel;
import com.grupo22.Leaf.domain.deck.Deck;
import com.grupo22.Leaf.edit.ListQuizActivity;
import com.grupo22.Leaf.quizgame.GameActivity;

import java.util.ArrayList;
import java.util.List;

public class DecksAdapter extends RecyclerView.Adapter<DecksAdapter.DeckHolder> {

    private static List<DeckViewModel> mDataset;
    private static OnItemClickListener clickListener;

    public interface OnItemClickListener {
        public void onClick(View view, int position);
    }

    public static class DeckHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title, id;
        public ImageButton editButton;
        public DeckHolder row;

        public DeckHolder(View view) {
            super(view);
            row = this;
            title = view.findViewById(R.id.deck_title);
            id = view.findViewById(R.id.deck_id);
            editButton = view.findViewById(R.id.edit_deck_but);
            view.setOnClickListener(this);
        }
        public void bind(DeckViewModel deckViewModel) {
            title.setText(deckViewModel.getTitle());
            id.setText(deckViewModel.getId());
            editButton.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View view) {
                    //Intent
                    DeckViewModel deckViewModel = mDataset.get(row.getAdapterPosition());

                    Deck deck = new Deck(deckViewModel.getId(), deckViewModel.getTitle(), deckViewModel.getCategory(), deckViewModel.getLang(), deckViewModel.getQuizzes());

                    //Here we would create the intent and pass the deck
                    Intent intentShare = new Intent(view.getContext(), ListQuizActivity.class);
                    intentShare.putExtra(view.getContext().getString(R.string.DECK_KEY), deck);
                    view.getContext().startActivity(intentShare);
                    Log.d("_TAG", "The deck position is the following: " + row.getAdapterPosition());
                }
            });
        }

        @Override
        public void onClick(View view) {
            if(clickListener != null) {
                clickListener.onClick(view, getAdapterPosition());
            }
        }

    }
    public DecksAdapter() {

        mDataset = new ArrayList<>();
    }

    public void setClickListener(OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @NonNull
    @Override
    public DecksAdapter.DeckHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_deck, parent, false);
        return new DeckHolder(v);
    }
    @Override
    public void onBindViewHolder(DeckHolder holder, int position) {
        holder.bind(mDataset.get(position));
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void addItem(DeckViewModel deckViewModel) {
        mDataset.add(deckViewModel);
        notifyItemInserted(mDataset.size());
    }

    public void removeItem(int position) {
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    public DeckViewModel getItem(int position) {
        return mDataset.get(position);
    }

    public void setItems(List<DeckViewModel> deck) {
        mDataset = deck;
    }

    public void updateItem(DeckViewModel item,
                           int position) {

        mDataset.add(position, item);
        notifyItemChanged(position);
    }


}
