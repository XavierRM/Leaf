package com.grupo22.Leaf.domain.user;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class User {
    private String userId;
    private String name;
    private List<String> userDecks;

    public User(){

    }

    public User(String userId) {
        this.userId = userId;
        this.name="default";
        this.userDecks=new ArrayList<>();
    }

    public User(String userId, String name, List<String> userDecks) {
        this.userId = userId;
        this.name = name;
        this.userDecks = userDecks;
    }

    public String getUserId() {
        return userId;
    }

    @Exclude
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    @Exclude
    public void setName(String name) {
        this.name = name;
    }

    public List<String> getUserDecks() {
        return userDecks;
    }

    @Exclude
    public void setUserDecks(List<String> userDecks) {
        this.userDecks = userDecks;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();

        result.put("name",name);
        result.put("userDecks",userDecks);

        return result;
    }
}
