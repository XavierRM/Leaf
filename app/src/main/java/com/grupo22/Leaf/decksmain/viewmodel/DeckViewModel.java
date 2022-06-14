package com.grupo22.Leaf.decksmain.viewmodel;

import com.grupo22.Leaf.domain.quiz.Quiz;

import java.util.List;

public class DeckViewModel {

    private String id;
    private String title;
    private String category;
    private String lang;
    private List<Quiz> questions;

    public DeckViewModel(String id, String title, String category, String lang, List<Quiz> questions) {

        this.id = id;
        this.title = title;
        this.questions = questions;
        this.category = category;
        this.lang = lang;
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

    public String getCategory() {
        return category;
    }

    public String getLang() {
        return lang;
    }
}
