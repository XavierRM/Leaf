package com.grupo22.Leaf.edit;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
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
import com.grupo22.Leaf.edit.presenter.EditQuizPresenter;
import com.grupo22.Leaf.edit.presenter.EditQuizPresenterImp;
import com.grupo22.Leaf.edit.presenter.QuizView;
import com.grupo22.Leaf.quizgame.viewmodel.QuizViewModel;

public class EditQuizActivity extends AppCompatActivity implements QuizView {

    EditText questionEt, rightAnswerEt;
    LinearLayout answersContainer;
    Button saveButton;
    Quiz quiz;

    private EditQuizPresenter mPresenter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_quiz);

        //Get the deck from the intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Quiz quiz = bundle.getParcelable(getString(R.string.QUIZ_KEY));
            if (quiz != null) {
                this.quiz = quiz;
            }
        }

        questionEt = findViewById(R.id.edit_question_et);
        answersContainer = findViewById(R.id.edit_answersContainer);
        rightAnswerEt = findViewById(R.id.edit_rightAnswer_et);
        saveButton = findViewById(R.id.edit_quizSave_button);

        setUpView();

        mPresenter = new EditQuizPresenterImp(this);
        mPresenter.initFlow();

    }

    private void setUpView() {

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Here we would save the edited data
                onBackPressed();

                Log.d("_TAG", "Save button for the quiz changes:\n");
            }
        });

    }

    public Quiz getQuiz() {
        return quiz;
    }

    @Override
    public void showQuiz(QuizViewModel quizViewModel) {
        questionEt.setText(quizViewModel.getQuestion());

        for(String answer: quizViewModel.getAnswers()) {
            EditText editText = new EditText(this);
            editText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            editText.setText(answer);
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