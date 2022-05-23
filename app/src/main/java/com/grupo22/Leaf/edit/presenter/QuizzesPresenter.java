package com.grupo22.Leaf.edit.presenter;

import com.grupo22.Leaf.decksmain.viewmodel.DeckViewModel;
import com.grupo22.Leaf.quizgame.viewmodel.QuizViewModel;

public interface QuizzesPresenter {

    void initFlow();

    void onClickQuiz(QuizViewModel quizViewModel);
}
