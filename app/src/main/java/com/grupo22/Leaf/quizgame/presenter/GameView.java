package com.grupo22.Leaf.quizgame.presenter;

import com.grupo22.Leaf.quizgame.viewmodel.QuizViewModel;

public interface GameView {

    void showQuiz(QuizViewModel question);

    void showAnswerSelected(int i);

    void showHit();

    void showMiss();

    void updateQuizzesCount(int currentQuiz, int totalQuizzes);

    void showEndScreen(int hits, int misses);
}
