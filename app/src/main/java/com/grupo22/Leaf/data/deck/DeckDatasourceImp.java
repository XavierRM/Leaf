package com.grupo22.Leaf.data.deck;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.grupo22.Leaf.domain.deck.Deck;
import com.grupo22.Leaf.domain.deck.datasource.DeckDatasource;
import com.grupo22.Leaf.domain.deck.service.DeckService;

import java.util.ArrayList;
import java.util.List;

public class DeckDatasourceImp implements DeckDatasource {

    private FirebaseDatabase database;
    private final String FIREBASE_DB_URL = "https://leaf-c7512-default-rtdb.europe-west1.firebasedatabase.app/";

    public DeckDatasourceImp() {
        database = FirebaseDatabase.getInstance(FIREBASE_DB_URL);
    }

    @Override
    public void searchDecks(String textToSearch, DeckService.OnResultListener<List<Deck>> onResultListener) {
        DatabaseReference decksRef = database.getReference("decks");

        decksRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    //Log.d("firebase", String.valueOf(task.getResult().getValue(Deck.class)));

                    List<Deck> decks = new ArrayList<>();

                    for (DataSnapshot deckSnapshot : task.getResult().getChildren()) {
                        Deck deck = deckSnapshot.getValue(Deck.class);
                        decks.add(deck);
                    }

                    onResultListener.onResult(decks);
                }
            }
        });
    }
}
