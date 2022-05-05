package com.grupo22.Leaf.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.grupo22.Leaf.R;
import com.grupo22.Leaf.domain.deck.Deck;
import com.grupo22.Leaf.quizgame.presenter.GameView;
import com.grupo22.Leaf.quizgame.presenter.QuizPresenter;
import com.grupo22.Leaf.quizgame.presenter.QuizPresenterImp;
import com.grupo22.Leaf.quizgame.viewmodel.QuizViewModel;

import java.util.List;

public class GameActivity extends AppCompatActivity implements GameView {

    private static final String DECK_KEY = "DECK_KEY";
    private QuizPresenter mPresenter;

    TextView question;
    TextView progress;
    List<Button> answers;
    Deck deck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = findViewById(R.id.quiz_question);
        progress = findViewById(R.id.quizzes_progress);
        answers.add(findViewById(R.id.but_first_answer));
        answers.add(findViewById(R.id.but_second_answer));
        answers.add(findViewById(R.id.but_third_answer));
        answers.add(findViewById(R.id.but_fourth_answer));

        setUpView();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Deck deck = bundle.getParcelable(GameActivity.DECK_KEY);
            if (deck != null) {
                this.deck = deck;
            }
        }

        mPresenter = new QuizPresenterImp(this);
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
    }

    public Deck getDeck() {
        return deck;
    }

    @Override
    public void showQuiz(QuizViewModel quiz) {
        this.question.setText(quiz.getName());
        List<String> answers = quiz.getAnswers();
        for (int i = 0; i < answers.size(); ++i) {
            this.answers.get(i).setText(answers.get(i));
        }
    }

    @Override
    public void showAnswerSelected(int i) {
        Button button = answers.get(i);
        button.setBackgroundColor(Color.BLUE);
    }

    @Override
    public void showHit() {
        Toast.makeText(getApplicationContext(), getString(R.string.hit), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMiss() {
        Toast.makeText(getApplicationContext(), getString(R.string.miss), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateQuizzesCount(int currentQuiz, int totalQuizzes) {

    }
}