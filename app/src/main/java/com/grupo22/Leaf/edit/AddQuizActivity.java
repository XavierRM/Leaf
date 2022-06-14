package com.grupo22.Leaf.edit;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.grupo22.Leaf.R;
import com.grupo22.Leaf.domain.quiz.Quiz;
import com.grupo22.Leaf.edit.presenter.AddQuizPresenter;
import com.grupo22.Leaf.edit.presenter.AddQuizPresenterImp;
import com.grupo22.Leaf.edit.presenter.QuizView;
import com.grupo22.Leaf.quizgame.viewmodel.QuizViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddQuizActivity extends AppCompatActivity implements QuizView {

    List<EditText> answers_et = new ArrayList<>();
    EditText questionEt, rightAnswerEt;
    LinearLayout answersContainer;
    Button saveButton;
    Quiz quiz;

    private AddQuizPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_quiz);

        questionEt = findViewById(R.id.edit_question_et);
        answersContainer = findViewById(R.id.edit_answersContainer);
        rightAnswerEt = findViewById(R.id.edit_rightAnswer_et);
        saveButton = findViewById(R.id.edit_quizSave_button);

        setUpView();

        mPresenter = new AddQuizPresenterImp(this);
        mPresenter.initFlow();
    }

    private Quiz getQuiz() {
        List<String> answers = new ArrayList<>();

        for(EditText answer: answers_et) {
            answers.add(answer.getText().toString());
        }

        return new Quiz(questionEt.getText().toString(), answers, Integer.valueOf(rightAnswerEt.getText().toString()));
    }

    private void setUpView() {

        saveButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                quiz = getQuiz();

                Intent intent = new Intent();
                intent.putExtra(getString(R.string.QUIZ_KEY), quiz);
                setResult(RESULT_OK, intent);
                Log.d("_TAG", "Save button for the quiz changes:\n");

                finish();
            }
        });

    }

    @Override
    public void showQuiz(QuizViewModel quizViewModel) {
        questionEt.setText(quizViewModel.getQuestion());

        for(String answer: quizViewModel.getAnswers()) {
            EditText editText = new EditText(this);
            editText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            editText.setText(answer);
            answers_et.add(editText);
            answersContainer.addView(editText);
        }

        rightAnswerEt.setText(Integer.toString(quizViewModel.getRightAnswer()));

    }

    @Override
    public void showEmptyView() {
        questionEt.setVisibility(View.GONE);
        rightAnswerEt.setVisibility(View.GONE);
        answersContainer.setVisibility(View.GONE);
        saveButton.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        Toast.makeText(this, R.string.general_error, Toast.LENGTH_SHORT).show();
    }
}