package com.grupo22.Leaf.edit;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.grupo22.Leaf.R;
import com.grupo22.Leaf.domain.deck.Deck;
import com.grupo22.Leaf.domain.quiz.Quiz;
import com.grupo22.Leaf.edit.adapter.QuizzesAdapter;
import com.grupo22.Leaf.edit.presenter.QuizzesPresenter;
import com.grupo22.Leaf.edit.presenter.QuizzesPresenterImp;
import com.grupo22.Leaf.edit.presenter.QuizzesView;
import com.grupo22.Leaf.quizgame.viewmodel.QuizViewModel;
import com.grupo22.Leaf.quizgame.viewmodel.QuizzesViewModelMapper;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ListQuizActivity extends AppCompatActivity implements QuizzesView {

    RecyclerView mRecycler;
    TextView mEmptyView;
    Button addQuizButton;
    Deck deck;

    ProgressDialog progressDialog;

    private QuizzesAdapter mAdapter;

    private QuizzesPresenter mPresenter;

    ActivityResultLauncher<Intent> my_startActivityForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == AppCompatActivity.RESULT_OK) {
                    Intent data = result.getData();
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        Quiz quiz = bundle.getParcelable(getString(R.string.QUIZ_KEY));
                        if (quiz != null) {
                            List<Quiz> quizzes = deck.getQuizzes();
                            quizzes.add(quiz);
                            QuizzesViewModelMapper quizzesViewModelMapper = new QuizzesViewModelMapper(quizzes);
                            mAdapter.setItems(quizzesViewModelMapper.map());
                        }
                    }
                }
            }
    );

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_quiz);

        //Get the deck from the intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Deck deck = bundle.getParcelable(getString(R.string.DECK_KEY));
            if (deck != null) {
                this.deck = deck;
            }
        }

        mRecycler = findViewById(R.id.quizzes_list);
        mEmptyView = findViewById(R.id.quizzes_empty_list);
        addQuizButton = findViewById(R.id.add_quiz_button);

        setUpView();

        mPresenter = new QuizzesPresenterImp(this);
        mPresenter.initFlow();

    }

    private void setUpView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(linearLayoutManager);

        mAdapter = new QuizzesAdapter();

        mAdapter.setClickListener(new QuizzesAdapter.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view, int position) {
                QuizViewModel quizViewModel = mAdapter.getItem(position);

                Quiz quiz = new Quiz(quizViewModel.getQuestion(), quizViewModel.getAnswers(), quizViewModel.getRightAnswer());

                //Here we would create the intent and pass the deck
                Intent intent = new Intent(getBaseContext(), EditQuizActivity.class);
                intent.putExtra(getString(R.string.QUIZ_KEY), quiz);
                startActivity(intent);
                Log.d("_TAG", "The deck selected is the following:\n" + quizViewModel.getQuestion() + "\n" + quizViewModel.getAnswers().size());
            }
        });

        addQuizButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AddQuizActivity.class);
                //intent.putExtra(getString(R.string.DECK_KEY), deck);
                my_startActivityForResult.launch(intent);
            }
        });
    }

    public Deck getDeck() {
        return deck;
    }

    @Override
    public void showQuizzes(List<QuizViewModel> quizzes) {
        mAdapter.setItems(quizzes);
        mEmptyView.setVisibility(View.GONE);
        mRecycler.setVisibility(View.VISIBLE);
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void showEmptyView() {
        mEmptyView.setVisibility(View.VISIBLE);
        mRecycler.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        Toast.makeText(this, R.string.general_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateQuiz(QuizViewModel quiz, int position) {
        mAdapter.updateItem(quiz, position);
    }
}