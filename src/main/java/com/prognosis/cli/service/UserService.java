package com.prognosis.cli.service;

import com.prognosis.cli.utils.BashRunner;

public class UserService {
    private final BashRunner bashRunner = new BashRunner();
    public String loginUser(String username, String password) {
        StringBuilder result = new StringBuilder();
        // Implement actual user validation logic
        try {
            // Execute the login_user method from the user_login.sh script
            return this.bashRunner.execute("user_login.sh", new String[]{username, password});
        } catch (Exception e) {
            // Handle exception
            System.err.println("Error executing script: " + e.getMessage());
        }
    
        return result.toString();
    }
}