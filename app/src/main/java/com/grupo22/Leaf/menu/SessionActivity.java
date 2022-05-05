package com.grupo22.Leaf.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.grupo22.Leaf.R;
import com.grupo22.Leaf.menu.presenter.SessionPresenter;
import com.grupo22.Leaf.menu.presenter.SessionPresenterImp;
import com.grupo22.Leaf.menu.presenter.SessionView;
import com.grupo22.Leaf.menu.viewmodel.SessionViewModel;

public class SessionActivity extends AppCompatActivity implements SessionView {

    SharedPreferences mysharedPreferences;
    SessionPresenter mPresenter;

    EditText email_et, password_et;
    Button save_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        setupView();

        mPresenter = new SessionPresenterImp(this);
        mPresenter.initFlow();

    }

    private void setupView() {
        email_et = findViewById(R.id.email_session_et);
        password_et = findViewById(R.id.password_session_et);
        save_bt = findViewById(R.id.save_session_but);

        mysharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        save_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = email_et.getText().toString();
                text = text + " ";
                text = text + password_et.getText().toString();

                SharedPreferences.Editor editor = mysharedPreferences.edit();
                editor.putString(getString(R.string.credentials), text);
                editor.apply();
            }
        });

        mPresenter = new SessionPresenterImp(this);
    }

    @Override
    public void showSessionData(SessionViewModel sessionViewModel) {
        email_et.setText(sessionViewModel.getEmail());
        password_et.setText(sessionViewModel.getPassword());
    }

    @Override
    public void showEmptyView() {
        email_et.setVisibility(View.GONE);
        password_et.setVisibility(View.GONE);
        save_bt.setVisibility(View.GONE);
    }

    @Override
    public void showError() {

    }
}