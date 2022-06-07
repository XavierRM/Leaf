package com.grupo22.Leaf.data.user;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.grupo22.Leaf.domain.user.User;
import com.grupo22.Leaf.domain.user.datasource.UserDatasource;
import com.grupo22.Leaf.domain.user.service.UserService;

import java.util.HashMap;
import java.util.Map;

public class UserDatasourceImp implements UserDatasource {

    private FirebaseDatabase database;
    private final String FIREBASE_DB_URL = "https://leaf-c7512-default-rtdb.europe-west1.firebasedatabase.app/";
    private final String USERS_ENDPOINT = "users";

    public UserDatasourceImp() {
        this.database = FirebaseDatabase.getInstance(FIREBASE_DB_URL);
    }

    @Override
    public void existsUser(String userId, UserService.OnResultListener<Boolean> onResultListener){
        DatabaseReference rootRef = database.getReference(USERS_ENDPOINT+"/"+userId);
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    onResultListener.onResult(true);
                } else {
                    onResultListener.onResult(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                onResultListener.onResult(false);
            }
        });
    }

    @Override
    public void getUser(String uid, UserService.OnResultListener<User> onResultListener) {
        DatabaseReference rootRef = database.getReference(USERS_ENDPOINT+"/"+uid);
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                user.setUserId(uid);
                onResultListener.onResult(user);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                onResultListener.onResult(null);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void createUser(User user) {
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(USERS_ENDPOINT +"/"+ user.getUserId(), user.toMap());

        database.getReference().updateChildren(childUpdates);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void updateUser(User user) {
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(USERS_ENDPOINT +"/"+ user.getUserId(), user.toMap());

        database.getReference().updateChildren(childUpdates);
    }
}
