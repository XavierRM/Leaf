package com.grupo22.Leaf.edit.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.grupo22.Leaf.R;
import com.grupo22.Leaf.domain.quiz.Quiz;
import com.grupo22.Leaf.edit.EditQuizActivity;
import com.grupo22.Leaf.edit.ListQuizActivity;
import com.grupo22.Leaf.quizgame.viewmodel.QuizViewModel;
import com.grupo22.Leaf.quizgame.viewmodel.QuizzesViewModelMapper;

import java.util.ArrayList;
import java.util.List;

public class EditQuizPresenterImp implements EditQuizPresenter {

    private QuizView quizView;

    private QuizViewModel mQuizViewModel;

    public EditQuizPresenterImp(QuizView view) {

        quizView = view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void initFlow() {
        // Añadir onError

        new GetQuizzesTask().execute();

    }

    private class GetQuizzesTask extends AsyncTask<String, Void, Quiz> {

        @RequiresApi(api = Build.VERSION_CODES.O)
        protected Quiz doInBackground(String... textToSearch) {

            return ((EditQuizActivity) quizView).getQuiz();

        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        protected void onPostExecute(Quiz result) {

            mQuizViewModel = new QuizViewModel(result.getQuestion(), result.getAnswers(), result.getRightAnswer());;
            quizView.showQuiz(mQuizViewModel);
        }
    }
}
