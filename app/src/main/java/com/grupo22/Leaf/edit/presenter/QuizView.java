package com.grupo22.Leaf.edit.presenter;

import com.grupo22.Leaf.quizgame.viewmodel.QuizViewModel;

import java.util.List;

public interface QuizView {

    void showQuiz(QuizViewModel quizViewModel);

    void showEmptyView();

    void showError();

}
