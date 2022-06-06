package com.grupo22.Leaf.domain.user.service;

import com.grupo22.Leaf.data.user.UserDatasourceImp;
import com.grupo22.Leaf.domain.user.User;
import com.grupo22.Leaf.domain.user.datasource.UserDatasource;

public class UserServiceImp implements UserService{

    private UserDatasource mDatasource= new UserDatasourceImp();

    @Override
    public void existsUser(String uid, OnResultListener<Boolean> onResultListener) {
        mDatasource.existsUser(uid, onResultListener);
    }

    @Override
    public void getUser(String uid, OnResultListener<User> onResultListener) {
        mDatasource.getUser(uid, onResultListener);
    }

    @Override
    public void createUser(User user) {
        mDatasource.createUser(user);
    }

    @Override
    public void updateUser(User user) {
        mDatasource.updateUser(user);
    }
}
