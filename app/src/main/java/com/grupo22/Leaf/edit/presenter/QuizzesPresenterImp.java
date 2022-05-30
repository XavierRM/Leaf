package com.grupo22.Leaf.edit.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.grupo22.Leaf.R;
import com.grupo22.Leaf.domain.quiz.Quiz;
import com.grupo22.Leaf.edit.ListQuizActivity;
import com.grupo22.Leaf.quizgame.viewmodel.QuizViewModel;
import com.grupo22.Leaf.quizgame.viewmodel.QuizzesViewModelMapper;

import java.util.ArrayList;
import java.util.List;

public class QuizzesPresenterImp implements QuizzesPresenter {

    private QuizzesView quizzesView;

    private List<QuizViewModel> mQuizzesViewModel;

    public QuizzesPresenterImp(QuizzesView view) {

        quizzesView = view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<QuizViewModel> getQuizzesViewModel(List<Quiz> quizzes) {

        mQuizzesViewModel = new QuizzesViewModelMapper(quizzes).map();
        return mQuizzesViewModel;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void initFlow() {
        // Añadir onError

        new GetQuizzesTask().execute();

    }

    @Override
    public void onClickQuiz(QuizViewModel quizViewModel) {
        Toast.makeText((Context) quizzesView, R.string.general_error, Toast.LENGTH_SHORT).show();
    }

    private class GetQuizzesTask extends AsyncTask<String, Void, List<Quiz>> {

        @RequiresApi(api = Build.VERSION_CODES.O)
        protected List<Quiz> doInBackground(String... textToSearch) {

            List<Quiz> quizzes = new ArrayList<>();

            quizzes = ((ListQuizActivity) quizzesView).getDeck().getQuizzes();

            return quizzes;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        protected void onPostExecute(List<Quiz> result) {

            mQuizzesViewModel = getQuizzesViewModel(result);
            quizzesView.showQuizzes(mQuizzesViewModel);
        }
    }
}
