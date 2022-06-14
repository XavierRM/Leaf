package com.grupo22.Leaf.menu.presenter;

import com.grupo22.Leaf.menu.viewmodel.SessionViewModel;

public interface SessionView {

    void showSessionData(SessionViewModel sessionViewModel);

    void showEmptyView();

    void showError();
}
