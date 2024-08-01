package com.prognosis.cli.model;

public class User {

    // Enum for role
    public enum Role {
        ADMIN, USER
    }

    // Properties
    private int id;
    private String email;
    private String password;
    private String code;

    public User() {
        // Constructor
    }

    public User(int id, String email, String password, String code) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.code = code;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}