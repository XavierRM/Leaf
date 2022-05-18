package com.grupo22.Leaf.domain.deck;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import com.grupo22.Leaf.domain.quiz.Quiz;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Deck implements Parcelable {

    private String id;
    private String title;
    private String category;
    private LocalDate creationDate = LocalDate.now();
    private LocalDate lastUpdate;
    private String lang;
    private List<Quiz> quizzes;

    public Deck(String id, String title, List<Quiz> quizzes) {
        this.id = id;
        this.title = title;
        this.creationDate = LocalDate.now();
        this.lastUpdate = LocalDate.now();
        this.quizzes = quizzes;
    }

    public Deck(String title, String category, String lang, List<Quiz> quizzes) {
        this.title = title;
        this.category = category;
        this.lang = lang;
        this.quizzes = quizzes;
        this.creationDate = LocalDate.now();
        this.lastUpdate = LocalDate.now();
    }

    public Deck(String title, String category, LocalDate creationDate, LocalDate lastUpdate,String lang, List<Quiz> quizzes) {
        this.title = title;
        this.category = category;
        this.lang = lang;
        this.quizzes = quizzes;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
    }

    protected Deck(Parcel in) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        id = in.readString();
        title = in.readString();
        category = in.readString();
        creationDate = LocalDate.parse((in.readString()), formatter);
        lastUpdate = LocalDate.parse((in.readString()), formatter);
        lang = in.readString();
        quizzes = new ArrayList<>();
        in.readTypedList(quizzes, Quiz.CREATOR);
    }

    public static final Creator<Deck> CREATOR = new Creator<Deck>() {
        @Override
        public Deck createFromParcel(Parcel in) {
            return new Deck(in);
        }

        @Override
        public Deck[] newArray(int size) {
            return new Deck[size];
        }
    };

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

    public void setTitle(String title) {
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

    public List<Quiz> getQuizzes() {
        updateLastDateTime();
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        updateLastDateTime();
        this.quizzes = quizzes;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(category);
        parcel.writeString(creationDate.toString());
        parcel.writeString(lastUpdate.toString());
        parcel.writeString(lang);
        parcel.writeTypedList(quizzes);
    }
}
