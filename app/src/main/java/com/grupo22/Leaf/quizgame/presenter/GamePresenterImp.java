package com.grupo22.Leaf.quizgame.presenter;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;

import androidx.annotation.RequiresApi;

import com.grupo22.Leaf.domain.deck.Deck;
import com.grupo22.Leaf.domain.quiz.Quiz;
import com.grupo22.Leaf.quizgame.GameActivity;
import com.grupo22.Leaf.quizgame.viewmodel.QuizViewModel;
import com.grupo22.Leaf.quizgame.viewmodel.QuizzesViewModelMapper;

import java.util.List;


public class GamePresenterImp implements GamePresenter {

    private GameView gameView;

    private List<QuizViewModel> mQuizzesViewModels;
    private int mCurrentQuiz = 0;
    private int mSelectedAnswer;
    private int hits = 0;
    private int misses = 0;

    public GamePresenterImp(GameView view) {
        gameView = view;
    }

    @Override
    public void initFlow() {
        new GetQuizzesTask().execute();
    }

    @Override
    public void onClickAnswer(int selectedAnswer) {
        mSelectedAnswer = selectedAnswer;
        gameView.showAnswerSelected(selectedAnswer);
    }

    @Override
    public void onCheckClick() {
        QuizViewModel currentQuiz = mQuizzesViewModels.get(mCurrentQuiz);
        if (currentQuiz.getRightAnswer() == mSelectedAnswer) {
            ++hits;
            gameView.showHit();
        }
        else {
            ++misses;
            gameView.showMiss();
        }

        if (++mCurrentQuiz < mQuizzesViewModels.size()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    gameView.updateQuizzesCount(mCurrentQuiz + 1, mQuizzesViewModels.size());
                    gameView.showQuiz(mQuizzesViewModels.get(mCurrentQuiz));
                }
            }, 1500);
        }
        else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    gameView.showEndScreen(hits, misses);
                }
            }, 1500);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<com.grupo22.Leaf.quizgame.viewmodel.QuizViewModel> getQuizzesViewModel(List<Quiz> quizzes) {

        mQuizzesViewModels = new QuizzesViewModelMapper(quizzes).map();
        return mQuizzesViewModels;
    }

    private class GetQuizzesTask extends AsyncTask<String, Void, List<Quiz>> {

        @RequiresApi(api = Build.VERSION_CODES.O)
        protected List<Quiz> doInBackground(String... textToSearch) {

            Deck deck = ((GameActivity) gameView).getDeck();

            return deck.getQuizzes();
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        protected void onPostExecute(List<Quiz> result) {

            mQuizzesViewModels = getQuizzesViewModel(result);

            gameView.showQuiz(mQuizzesViewModels.get(mCurrentQuiz));
            gameView.updateQuizzesCount(mCurrentQuiz + 1, mQuizzesViewModels.size());
        }
    }
}
