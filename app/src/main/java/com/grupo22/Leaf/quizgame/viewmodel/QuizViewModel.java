package com.grupo22.Leaf.quizgame.viewmodel;

import java.util.List;

public class QuizViewModel {
    private String name;
    private List<String> answers;
    private int rightAnswer;

    public QuizViewModel(String name, List<String> answers, int rightAnswer) {
        this.name = name;
        this.answers = answers;
        this.rightAnswer = rightAnswer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
}