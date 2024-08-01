package com.prognosis.cli.model;

public class User {

    public enum Role {
        ADMIN, PATIENT
    }

    public int id;
    public Role role;
    public String email;
    public String code;
    public String password;

    public User(int id, Role role, String email, String code, String password) {
        this.id = id;
        this.role = role;
        this.email = email;
        this.code = code;
        this.password = password;
    }
}