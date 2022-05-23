package com.grupo22.Leaf.edit.adapter;

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
import com.grupo22.Leaf.quizgame.viewmodel.QuizViewModel;

import java.util.ArrayList;
import java.util.List;

public class QuizzesAdapter extends RecyclerView.Adapter<QuizzesAdapter.QuizHolder> {

    private static List<QuizViewModel> mDataset;
    private static OnItemClickListener clickListener;

    public interface OnItemClickListener {
        public void onClick(View view, int position);
    }

    public static class QuizHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView question, nAnswers;
        public QuizHolder row;

        public QuizHolder(View view) {
            super(view);
            row = this;
            question = view.findViewById(R.id.editrv_quiz_question);
            nAnswers = view.findViewById(R.id.editrv_nanswers);
            view.setOnClickListener(this);
        }
        public void bind(QuizViewModel quizViewModel) {
            question.setText(quizViewModel.getQuestion());
            nAnswers.setText("Number of answers: " + Integer.toString(quizViewModel.getAnswers().size()));
        }

        @Override
        public void onClick(View view) {
            if(clickListener != null) {
                clickListener.onClick(view, getAdapterPosition());
            }
        }

    }
    public QuizzesAdapter() {

        mDataset = new ArrayList<>();
    }

    public void setClickListener(OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @NonNull
    @Override
    public QuizzesAdapter.QuizHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_quiz, parent, false);
        return new QuizHolder(v);
    }
    @Override
    public void onBindViewHolder(QuizHolder holder, int position) {
        holder.bind(mDataset.get(position));
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void addItem(QuizViewModel quizViewModel) {
        mDataset.add(quizViewModel);
        notifyItemInserted(mDataset.size());
    }

    public void removeItem(int position) {
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    public QuizViewModel getItem(int position) {
        return mDataset.get(position);
    }

    public void setItems(List<QuizViewModel> deck) {
        mDataset = deck;
    }

    public void updateItem(QuizViewModel item,
                           int position) {

        mDataset.add(position, item);
        notifyItemChanged(position);
    }


}
