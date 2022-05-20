package com.grupo22.Leaf.quizgame.viewmodel;

import android.os.Build;

import androidx.annotation.RequiresApi;
import com.grupo22.Leaf.domain.quiz.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizzesViewModelMapper {
    private List<Quiz> mQuizzes;

    public QuizzesViewModelMapper(List<Quiz> quizzes) {
        mQuizzes = quizzes;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<com.grupo22.Leaf.quizgame.viewmodel.QuizViewModel> map() {

        List<com.grupo22.Leaf.quizgame.viewmodel.QuizViewModel> quizzes = new ArrayList<>();

        for (Quiz quiz : mQuizzes) {
            quizzes.add(new com.grupo22.Leaf.quizgame.viewmodel.QuizViewModel(quiz.getQuestion(), quiz.getAnswers(), quiz.getRightAnswer()));
        }

        return quizzes;
    }
}
