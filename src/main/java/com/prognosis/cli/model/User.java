package com.prognosis.cli.model;

import com.prognosis.cli.view.UserView;

public abstract class User {

    public enum Role {
        ADMIN, PATIENT
    }

    public String id;
    public Role role;
    public String email;
    public String code;
    public String password;
    public String firstName;
    public String lastName;

    public User(String id, Role role, String email, String code, String password) {
        this.id = id;
        this.role = role;
        this.email = email;
        this.code = code;
        this.password = password;
    }

    public void displayMenu(UserView userView) {}
}