package com.grupo22.Leaf.quizgame.presenter;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;

import androidx.annotation.RequiresApi;

import com.grupo22.Leaf.domain.deck.Deck;
import com.grupo22.Leaf.domain.question.Quiz;
import com.grupo22.Leaf.quizgame.GameActivity;
import com.grupo22.Leaf.quizgame.viewmodel.QuizViewModel;
import com.grupo22.Leaf.quizgame.viewmodel.QuizzesViewModelMapper;

import java.util.List;


public class QuizPresenterImp implements QuizPresenter {

    private GameView gameView;

    private List<QuizViewModel> mQuizzesViewModels;
    private int mCurrentQuiz = 0;
    private int mSelectedAnswer;

    public QuizPresenterImp(GameView view) {
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
        if (currentQuiz.getRightAnswer() == mSelectedAnswer)
            gameView.showHit();
        else gameView.showMiss();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ++mCurrentQuiz;
                gameView.updateQuizzesCount(mCurrentQuiz + 1, mQuizzesViewModels.size());
                gameView.showQuiz(mQuizzesViewModels.get(mCurrentQuiz));
            }
        }, 1500);


        // show summary end screen when deck finished
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<QuizViewModel> getQuizzesViewModel(List<Quiz> quizzes) {

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
        }
    }

    /*private class AdvanceQuiz extends AsyncTask<String, Void, List<Deck>> {

        @RequiresApi(api = Build.VERSION_CODES.O)
        protected List<Deck> doInBackground(String... textToSearch) {
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        protected void onPostExecute(List<Deck> result) {
            ++mCurrentQuiz;
            gameView.updateQuizzesCount(mCurrentQuiz + 1, mQuizzesViewModels.size());
            gameView.showQuiz(mQuizzesViewModels.get(mCurrentQuiz));
        }
    }*/
}
