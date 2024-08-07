package com.prognosis.cli.model;
import java.util.List;

public class Admin extends User {    
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