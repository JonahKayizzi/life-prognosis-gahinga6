package com.prognosis.cli.model;
import java.util.List;
import java.util.UUID;

import com.prognosis.cli.repository.AdminRepository;


public class Admin extends User {
    public static String createUser(String email) {
        Integer usersCount = AdminRepository.countUsers();
        Integer nextUserId = ++usersCount;
        String code = UUID.randomUUID().toString();
        


        String[] command = {"bash", "-c", String.format("echo \"%d, %s, %s\" >> user-store.txt",nextUserId, email, code)};

        try{
            Process process = Runtime.getRuntime().exec(command);
        }catch  (Exception e) {
            e.printStackTrace();
        }

        return code;
    }
    
    private void ExportReport(List<User> users) {
        // Export report
    }

    private void GenerateAnalytics(List<User> users) {
        // Generate analytics
    }
}