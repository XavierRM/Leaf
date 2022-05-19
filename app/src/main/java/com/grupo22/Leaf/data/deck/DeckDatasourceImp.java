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

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.grupo22.Leaf.domain.deck.Deck;
import com.grupo22.Leaf.domain.deck.conversor.JsonToDeckConversor;
import com.grupo22.Leaf.domain.deck.datasource.DeckDatasource;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class DeckDatasourceImp implements DeckDatasource {
    @Override
    public List<Deck> searchDecks(String textToSearch) {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://leaf-c7512-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("decks/0");

        myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    try {
                        Deck deck = JsonToDeckConversor.convert(task.getResult().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return new ArrayList<>();
    }
}
