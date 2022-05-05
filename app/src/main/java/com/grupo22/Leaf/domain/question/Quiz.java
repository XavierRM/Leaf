package com.grupo22.Leaf.domain.question;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Quiz implements Parcelable {
    private String name;
    private List<String> answers;
    private int rightAnswer;

    public Quiz(String name, List<String> answers, int rightAnswer) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeList(answers);
        parcel.writeInt(rightAnswer);
    }
}
