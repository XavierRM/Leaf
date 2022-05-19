package com.grupo22.Leaf.quizgame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.grupo22.Leaf.R;
import com.grupo22.Leaf.domain.deck.Deck;
import com.grupo22.Leaf.quizgame.presenter.GameView;
import com.grupo22.Leaf.quizgame.presenter.GamePresenter;
import com.grupo22.Leaf.quizgame.presenter.GamePresenterImp;
import com.grupo22.Leaf.quizgame.viewmodel.QuizViewModel;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity implements GameView {

    public static final String DECK_KEY = "DECK_KEY";
    private GamePresenter mPresenter;

    TextView question;
    TextView progress;
    List<Button> answers = new ArrayList<>();
    Button checkAnswerButton;
    Deck deck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        question = findViewById(R.id.quiz_question);
        progress = findViewById(R.id.quizzes_progress);
        answers.add(findViewById(R.id.but_first_answer));
        answers.add(findViewById(R.id.but_second_answer));
        answers.add(findViewById(R.id.but_third_answer));
        answers.add(findViewById(R.id.but_fourth_answer));
        checkAnswerButton = findViewById(R.id.but_check_answer);

        setUpView();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Deck deck = bundle.getParcelable(GameActivity.DECK_KEY);
            if (deck != null) {
                this.deck = deck;
            }
        }

        mPresenter = new GamePresenterImp(this);
        mPresenter.initFlow();
    }

    private void setUpView() {
        for (Button answer : answers)
            answer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (view.getId()) {
                        case R.id.but_first_answer:
                            mPresenter.onClickAnswer(0);
                            break;
                        case R.id.but_second_answer:
                            mPresenter.onClickAnswer(1);
                            break;
                        case R.id.but_third_answer:
                            mPresenter.onClickAnswer(2);
                            break;
                        case R.id.but_fourth_answer:
                            mPresenter.onClickAnswer(3);
                            break;
                    }
                }
            });

        checkAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onCheckClick();
            }
        });
    }

    public Deck getDeck() {
        return deck;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void showQuiz(QuizViewModel quiz) {
        checkAnswerButton.setBackgroundColor(getColor(com.google.android.material.R.color.design_default_color_secondary));
        this.question.setText(quiz.getName());
        List<String> answers = quiz.getAnswers();
        for (int i = 0; i < answers.size(); ++i) {
            this.answers.get(i).setText(answers.get(i));
            this.answers.get(i).setBackgroundColor(getColor(com.google.android.material.R.color.design_default_color_primary));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void showAnswerSelected(int i) {
        for (int j = 0; j < answers.size(); ++j) {
            Button currentButton = answers.get(j);
            if (j == i)
                currentButton.setBackgroundColor(Color.BLUE);
            else currentButton.setBackgroundColor(getColor(com.google.android.material.R.color.design_default_color_primary));
        }
    }

    @Override
    public void showHit() {
        checkAnswerButton.setBackgroundColor(Color.GREEN);
        Toast.makeText(getApplicationContext(), getString(R.string.hit), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMiss() {
        checkAnswerButton.setBackgroundColor(Color.RED);
        Toast.makeText(getApplicationContext(), getString(R.string.miss), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateQuizzesCount(int currentQuiz, int totalQuizzes) {
        progress.setText(currentQuiz + "/" + totalQuizzes);
    }

    @Override
    public void showEndScreen() {
        Log.d("END", "END");
    }
}