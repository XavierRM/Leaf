package com.grupo22.Leaf.edit.presenter;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.grupo22.Leaf.domain.quiz.Quiz;
import com.grupo22.Leaf.quizgame.viewmodel.QuizViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddQuizPresenterImp implements AddQuizPresenter{

    private QuizView quizView;

    private QuizViewModel mQuizViewModel;

    public AddQuizPresenterImp(QuizView view) {

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

            List<String> answers = new ArrayList<>();

            for(int i = 0; i < 4; i++) {
                answers.add("Answer " + (i + 1));
            }

            return new Quiz("Question", answers, 1);

        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        protected void onPostExecute(Quiz result) {

            mQuizViewModel = new QuizViewModel(result.getQuestion(), result.getAnswers(), result.getRightAnswer());;
            quizView.showQuiz(mQuizViewModel);
        }
    }
}
