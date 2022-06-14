package com.grupo22.Leaf.domain.user.service;

import com.grupo22.Leaf.domain.user.User;

public interface UserService {

    interface OnResultListener<TResult> {
        void onResult(TResult result);
    }

    void existsUser(String uid, OnResultListener<Boolean> onResultListener);

    void getUser(String uid, OnResultListener<User> onResultListener);

    void createUser(User user);

    void updateUser(User user);
}
