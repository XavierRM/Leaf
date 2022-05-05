package com.grupo22.Leaf.menu.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import com.grupo22.Leaf.R;
import com.grupo22.Leaf.menu.viewmodel.SessionViewModel;
import java.util.Arrays;
import java.util.List;

public class SessionPresenterImp implements SessionPresenter{

    private SessionView sessionView;

    private SessionViewModel sessionViewModel;

    SharedPreferences mysharedPreferences;


    public SessionPresenterImp(SessionView view) {
        sessionView = view;
    }

    private SessionViewModel getSessionViewModel(String credentials) {
        List<String> credentialsParsed = Arrays.asList(credentials.split(" "));

        if(credentialsParsed.size() == 1)
            return new SessionViewModel(credentialsParsed.get(0), "");

        return new SessionViewModel(credentialsParsed.get(0), credentialsParsed.get(1));
    }

    @Override
    public void initFlow() {

        new GetCredentialsTask().execute();

    }

    @Override
    public void onClickButton() {

    }

    private class GetCredentialsTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... String) {

            mysharedPreferences = PreferenceManager.getDefaultSharedPreferences((Context) sessionView);

            //If there's nothing saved on sharedPreferences the we dont't display anything
            String aux = mysharedPreferences.getString(((Context) sessionView).getApplicationContext().getString(R.string.credentials), "");

            return aux;
        }

        protected void onPostExecute(String result) {

            sessionViewModel = getSessionViewModel(result);
            sessionView.showSessionData(sessionViewModel);
        }
    }
}
