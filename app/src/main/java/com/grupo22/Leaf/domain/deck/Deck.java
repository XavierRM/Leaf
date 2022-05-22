package com.grupo22.Leaf.domain.deck;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import com.google.firebase.database.Exclude;
import com.grupo22.Leaf.domain.quiz.Quiz;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Deck implements Parcelable {

    private String id;
    private String title;
    private String category;
    private LocalDate creationDate;
    private LocalDate lastUpdate;
    private String lang;
    private List<Quiz> quizzes;

    private final String DATE_PATTERN = "yyyy-MM-dd";

    public Deck() {
    }

    public Deck(String title, String category, String lang, List<Quiz> quizzes) {
        this.title = title;
        this.creationDate = LocalDate.now();
        this.lastUpdate = LocalDate.now();
        this.quizzes = quizzes;
        this.category = category;
        this.lang = lang;
    }

    public Deck(String id, String title, String category, String lang, List<Quiz> quizzes) {
        this.id = id;
        this.title = title;
        this.creationDate = LocalDate.now();
        this.lastUpdate = LocalDate.now();
        this.quizzes = quizzes;
        this.category = category;
        this.lang = lang;
    }

    protected Deck(Parcel in) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

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

    private void updateLastModificationTime() {
        this.lastUpdate = LocalDate.now();
    }

    public String getId() {
        return id;
    }

    @Exclude
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    @Exclude
    public void setTitle(String title) {
        updateLastModificationTime();
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    @Exclude
    public void setCategory(String category) {
        updateLastModificationTime();
        this.category = category;
    }

    public String getLang() {
        return lang;
    }

    @Exclude
    public void setLang(String lang) {
        updateLastModificationTime();
        this.lang = lang;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    @Exclude
    public void setQuizzes(List<Quiz> quizzes) {
        updateLastModificationTime();
        this.quizzes = quizzes;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        this.creationDate = LocalDate.parse(creationDate, formatter);
    }

    public String getLastUpdate() {
        return lastUpdate.toString();
    }

    public void setLastUpdate(String lastUpdate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        this.lastUpdate = LocalDate.parse(lastUpdate, formatter);
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

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("category", category);
        result.put("creationDate", creationDate.toString());
        result.put("lang", lang);
        result.put("lastUpdate", lastUpdate.toString());
        result.put("quizzes", quizzes.stream().map(Quiz::toMap).collect(Collectors.toList()));
        result.put("title", title);

        return result;
    }
}
