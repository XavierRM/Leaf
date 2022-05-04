package com.grupo22.Leaf.domain.deck;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.grupo22.Leaf.domain.question.Question;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Deck {

    private String id;
    private String title;
    private String category;
    private final LocalDate creationDate;
    private LocalDate lastUpdate;
    private String lang;
    private List<Question> questions;

    public Deck(String id, String title) {
        this.id = id;
        this.title = title;
        this.creationDate = LocalDate.now();
        this.lastUpdate = LocalDate.now();
    }

    public Deck(String title, String category, String lang, List<Question> questions) {
        this.title = title;
        this.category = category;
        this.lang = lang;
        this.questions = questions;
        this.creationDate = LocalDate.now();
        this.lastUpdate = LocalDate.now();
    }

    public Deck(String title, String category, LocalDate creationDate, LocalDate lastUpdate,String lang, List<Question> questions) {
        this.title = title;
        this.category = category;
        this.lang = lang;
        this.questions = questions;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
    }

    private void updateLastDateTime(){
        this.lastUpdate = LocalDate.now();
    }

    public String getId() {
        updateLastDateTime();
        return id;
    }

    public void setId(String id) {
        updateLastDateTime();
        this.id = id;
    }

    public String getTitle() {
        updateLastDateTime();
        return title;
    }

    public void setTittle(String title) {
        updateLastDateTime();
        this.title = title;
    }

    public String getCategory() {
        updateLastDateTime();
        return category;
    }

    public void setCategory(String category) {
        updateLastDateTime();
        this.category = category;
    }

    public String getLang() {
        updateLastDateTime();
        return lang;
    }

    public void setLang(String lang) {
        updateLastDateTime();
        this.lang = lang;
    }

    public List<Question> getQuestions() {
        updateLastDateTime();
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        updateLastDateTime();
        this.questions = questions;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }
}
