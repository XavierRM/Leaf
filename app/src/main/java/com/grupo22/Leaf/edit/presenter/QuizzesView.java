package com.grupo22.Leaf.edit.presenter;

import com.grupo22.Leaf.quizgame.viewmodel.QuizViewModel;

import java.util.List;

public interface QuizzesView {

    void showQuizzes(List<QuizViewModel> quizzes);

    void showEmptyView();

    void showError();

    void updateQuiz(QuizViewModel quiz, int position);
}
