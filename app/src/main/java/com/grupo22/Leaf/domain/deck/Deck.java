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

    private String tittle;
    private String category;
    private final LocalDate creationDate;
    private LocalDate lastUpdate;
    private String lang;
    private List<Question> questions;

    public Deck(String tittle, String category, String lang, List<Question> questions) {
        this.tittle = tittle;
        this.category = category;
        this.lang = lang;
        this.questions = questions;
        this.creationDate = LocalDate.now();
        this.lastUpdate = LocalDate.now();
    }

    private void updateLastDateTime(){
        this.lastUpdate = LocalDate.now();
    }

    public String getTittle() {
        updateLastDateTime();
        return tittle;
    }

    public void setTittle(String tittle) {
        updateLastDateTime();
        this.tittle = tittle;
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
}
