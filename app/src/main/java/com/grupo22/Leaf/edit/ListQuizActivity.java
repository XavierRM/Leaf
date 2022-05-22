package com.grupo22.Leaf.edit;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.grupo22.Leaf.R;
import com.grupo22.Leaf.domain.deck.Deck;
import com.grupo22.Leaf.quizgame.GameActivity;

public class ListQuizActivity extends AppCompatActivity {

    TextView title, id, count;
    Deck deck;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_quiz);

        title = findViewById(R.id.edit_title_tv);
        id = findViewById(R.id.edit_id_tv);
        count = findViewById(R.id.edit_count_tv);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Deck deck = bundle.getParcelable(getString(R.string.DECK_KEY));
            if (deck != null) {
                this.deck = deck;
            }
        }

        title.setText(deck.getTitle());
        id.setText(deck.getId());
        Log.d("_TAG", "The deck id is the following: " + deck.getId());
        count.setText(Integer.toString(deck.getQuizzes().size()));

    }
}