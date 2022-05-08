package com.grupo22.Leaf.module.viewmodel;

import com.grupo22.Leaf.domain.quiz.Quiz;

import java.util.List;

public class DeckViewModel {

    private String id;
    private String title;
    private List<Quiz> questions;

    public DeckViewModel(String id,
                           String title, List<Quiz> questions) {

        this.id = id;
        this.title = title;
        this.questions = questions;
    }

    public String getId() {

        return id;
    }

    public String getTitle() {

        return title;
    }

    public List<Quiz> getQuizzes() {
        return questions;
    }
}
