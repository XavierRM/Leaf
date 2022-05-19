package com.grupo22.Leaf.domain.quiz;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Quiz implements Parcelable {
    private String name;
    private List<String> answers;
    private int rightAnswer;

    public Quiz() {
    }

    public Quiz(String name, List<String> answers, int rightAnswer) {
        this.name = name;
        this.answers = answers;
        this.rightAnswer = rightAnswer;
    }

    protected Quiz(Parcel in) {
        name = in.readString();
        answers = new ArrayList<>();
        in.readStringList(answers);
        rightAnswer = in.readInt();
    }

    public static final Creator<Quiz> CREATOR = new Creator<Quiz>() {
        @Override
        public Quiz createFromParcel(Parcel in) {
            return new Quiz(in);
        }

        @Override
        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };

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
        parcel.writeStringList(answers);
        parcel.writeInt(rightAnswer);
    }
}
