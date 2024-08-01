package com.prognosis.cli.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    public String loginUser(String username, String password) {
        StringBuilder result = new StringBuilder();
        // Implement actual user validation logic
        try {
            // Execute the login_user method from the userCrud.sh script
            Process process = Runtime.getRuntime().exec(new String[] {"bash", "userCrud.sh", "login_user", username, password});

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            // Handle exception
            System.err.println("Error executing script: " + e.getMessage());
        }

        return result.toString();
    }


}