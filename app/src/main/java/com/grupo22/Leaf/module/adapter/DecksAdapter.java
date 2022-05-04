package com.grupo22.Leaf.module.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.grupo22.Leaf.R;
import com.grupo22.Leaf.module.viewmodel.DeckViewModel;

public class DecksAdapter extends Adapter<DecksAdapter.DeckHolder> {

    private List<DeckViewModel> mItems;

    public DecksAdapter() {

        mItems = new ArrayList<>();
    }

    public void setItems(List<DeckViewModel> items) {

        mItems = items;
        notifyDataSetChanged();
    }

    public void updateItem(DeckViewModel item,
                           int position) {

        mItems.add(position, item);
        notifyItemChanged(position);
    }

    @NonNull
    @Override
    public DecksAdapter.DeckHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                         int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_deck, parent, false);
        return new DeckHolder(v);
    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }

    @Override
    public void onBindViewHolder(@NonNull DecksAdapter.DeckHolder holder,
                                 int position) {

        holder.bind(mItems.get(position));
    }

    static class DeckHolder extends RecyclerView.ViewHolder {

        private TextView mTvId;
        private TextView mTvTitle;

        private DeckHolder(View v) {

            super(v);
            mTvId = v.findViewById(R.id.row_deck_id);
            mTvTitle = v.findViewById(R.id.row_deck_title);
        }

        private void bind(DeckViewModel deck) {

            mTvId.setText("-");
            String name = deck.getTitle();

            mTvTitle.setText(name);
        }
    }

}
