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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.grupo22.Leaf.R;
import com.grupo22.Leaf.domain.deck.Deck;
import com.grupo22.Leaf.domain.deck.service.DeckService;
import com.grupo22.Leaf.domain.deck.service.DeckServiceImp;
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
    EditText deckTitle;
    Button addQuizButton, saveButton;
    Deck deck;
    int lastEditPosition = 0;

    ProgressDialog progressDialog;

    private QuizzesAdapter mAdapter;
    private QuizzesPresenter mPresenter;
    private DeckService mDeckService = new DeckServiceImp();

    ActivityResultLauncher<Intent> my_startActivityForResultAddQuiz = registerForActivityResult(
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
                            deck.setQuizzes(quizzes);
                            QuizzesViewModelMapper quizzesViewModelMapper = new QuizzesViewModelMapper(quizzes);
                            mAdapter.setItems(quizzesViewModelMapper.map());
                        }
                    }
                }
            }
    );

    ActivityResultLauncher<Intent> my_startActivityForResultEditQuiz = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == AppCompatActivity.RESULT_OK) {
                    Intent data = result.getData();
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        Quiz quiz = bundle.getParcelable(getString(R.string.QUIZ_KEY));
                        if (quiz != null) {
                            List<Quiz> quizzes = deck.getQuizzes();
                            quizzes.set(lastEditPosition, quiz);
                            deck.setQuizzes(quizzes);
                            mAdapter.updateItem(new QuizViewModel(quiz.getQuestion(), quiz.getAnswers(), quiz.getRightAnswer()), lastEditPosition);
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
        deckTitle = findViewById(R.id.edit_deck_title_et);
        addQuizButton = findViewById(R.id.add_quiz_button);
        saveButton = findViewById(R.id.save_quizzes_button);

        setUpView();

        mPresenter = new QuizzesPresenterImp(this);
        mPresenter.initFlow();

    }

    private void setUpView() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                deck.setTitle(deckTitle.getText().toString());
                mDeckService.updateDeck(deck);
                finish();
            }
        });

        deckTitle.setText(deck.getTitle());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(linearLayoutManager);

        mAdapter = new QuizzesAdapter();

        mAdapter.setClickListener(new QuizzesAdapter.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view, int position) {
                lastEditPosition = position;
                QuizViewModel quizViewModel = mAdapter.getItem(position);

                Quiz quiz = new Quiz(quizViewModel.getQuestion(), quizViewModel.getAnswers(), quizViewModel.getRightAnswer());

                Intent intent = new Intent(getBaseContext(), EditQuizActivity.class);
                intent.putExtra(getString(R.string.QUIZ_KEY), quiz);
                my_startActivityForResultEditQuiz.launch(intent);

                Log.d("_TAG", "The deck selected is the following:\n" + quizViewModel.getQuestion() + "\n" + quizViewModel.getAnswers().size());
            }
        });

        addQuizButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AddQuizActivity.class);
                //intent.putExtra(getString(R.string.DECK_KEY), deck);
                my_startActivityForResultAddQuiz.launch(intent);
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