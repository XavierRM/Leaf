package com.grupo22.Leaf.menu.viewmodel;

public class SessionViewModel {

    private boolean registered;
    private String email;
    private String password;

    public SessionViewModel(String email,
                         String password) {

        this.email = email;
        this.password = password;
    }

    public String getEmail() {

        return email;
    }

    public String getPassword() {

        return password;
    }

}
