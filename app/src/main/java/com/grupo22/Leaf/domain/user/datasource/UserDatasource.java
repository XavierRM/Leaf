package com.grupo22.Leaf.domain.user.datasource;

import com.grupo22.Leaf.domain.user.User;
import com.grupo22.Leaf.domain.user.service.UserService;

public interface UserDatasource {

    void existsUser(String uid, UserService.OnResultListener<Boolean> onResultListener);

    void getUser(String uid, UserService.OnResultListener<User> onResultListener);

    void createUser(User user);

    void updateUser(User user);
}
