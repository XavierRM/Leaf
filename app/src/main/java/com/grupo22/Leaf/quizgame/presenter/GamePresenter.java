package com.grupo22.Leaf.quizgame.presenter;

public interface GamePresenter {

    void initFlow();

    void onClickAnswer(int selectedAnswer);

    void onCheckClick();
}
