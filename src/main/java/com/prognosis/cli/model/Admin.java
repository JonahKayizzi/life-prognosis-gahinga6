package com.prognosis.cli.model;
import java.util.List;

public class Admin extends User {    
    public Admin(int id, Role role, String email, String code, String password) {
        super(id, role, email, code, password);
    }

    private void ExportReport(List<User> users) {
        // Export report
    }

    private void GenerateAnalytics(List<User> users) {
        // Generate analytics
    }
}