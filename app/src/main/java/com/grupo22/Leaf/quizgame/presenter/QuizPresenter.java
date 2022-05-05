package com.grupo22.Leaf.quizgame.presenter;

import com.grupo22.Leaf.quizgame.viewmodel.QuizViewModel;

public interface QuizPresenter {

    void initFlow();

    void onClickAnswer(int selectedAnswer);

    void onCheckClick();
}
