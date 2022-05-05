package com.grupo22.Leaf.quizgame.viewmodel;

import android.os.Build;

import androidx.annotation.RequiresApi;
import com.grupo22.Leaf.domain.question.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizzesViewModelMapper {
    private List<Quiz> mQuizzes;

    public QuizzesViewModelMapper(List<Quiz> quizzes) {
        mQuizzes = quizzes;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<QuizViewModel> map() {

        List<QuizViewModel> quizzes = new ArrayList<>();

        for (Quiz quiz : mQuizzes) {
            quizzes.add(new QuizViewModel(quiz.getName(), quiz.getAnswers(), quiz.getRightAnswer()));
        }

        return quizzes;
    }
}
