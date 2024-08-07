package com.prognosis.cli.model;
import java.util.List;

// Import the User class
public class Admin extends User {   
    
    // Constructor
    public Admin(String id, String email, String code) {
        super(id, Role.ADMIN, email, code);
    }

    private void ExportReport(List<User> users) {
        // Export report
    }

    private void GenerateAnalytics(List<User> users) {
        // Generate analytics
    }
}