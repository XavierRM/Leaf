package com.grupo22.Leaf.data.deck;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.grupo22.Leaf.domain.deck.Deck;
import com.grupo22.Leaf.domain.deck.datasource.DeckDatasource;

import java.util.List;

public class DeckDatasourceImp implements DeckDatasource {
    @Override
    public List<Deck> searchDecks(String textToSearch) {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://leaf-c7512-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
        return null;
    }
}
