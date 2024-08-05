package com.prognosis.cli.service;

import com.prognosis.cli.model.Admin;
import com.prognosis.cli.model.Patient;
import com.prognosis.cli.model.User;
import com.prognosis.cli.utils.BashRunner;

public class UserService {
    private final BashRunner bashRunner = new BashRunner();
    public User loginUser(String username, String password) {
        // Implement actual user validation logic
        try {
            // Execute the login_user method from the user_login.sh script
            String output = this.bashRunner.execute("user_login.sh", new String[]{username, password});

            // Split the output into an array of strings
            String[] parts = output.split(" ");
            String userId = parts[0];
            String role = parts[1];

            // Check the role of the user
            if (role.equalsIgnoreCase("admin")) {
                return new Admin(userId, username, "ADMIN", password);
            } else if (role.equalsIgnoreCase("patient")) {
                return new Patient(userId, username, "PATIENT", password);
            } else {
                return null;
            }
        } catch (Exception e) {
            // Handle exception
            System.err.println("Error executing script: " + e.getMessage());
        }
        return null;
    }
}