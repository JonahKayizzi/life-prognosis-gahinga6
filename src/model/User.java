package model;

import view.UserView;

// Abstract class for User
public abstract class User {

    // Enum for Role
    public enum Role {
        ADMIN, PATIENT
    }

    // Properties
    public String id;
    public Role role;
    public String email;
    public String code;
    public String password;
    public String firstName;
    public String lastName;

    // Constructor
    public User(String id, Role role, String email, String code) {
        this.id = id;
        this.role = role;
        this.email = email;
        this.code = code;
    }

    // Abstract method for displaying menu
    public void displayMenu(UserView userView) {}
}