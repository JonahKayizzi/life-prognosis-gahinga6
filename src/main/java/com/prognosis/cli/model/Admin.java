package com.prognosis.cli.model;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.UUID;
import java.util.Scanner;

public class Admin extends User {

    private static Integer countUsers () {
        // Create user
        String[] command = {"bash", "-c", String.format("wc -l < \"user-store.txt\"")};

        Integer numOfUsers = 0;

        try{
            Process process = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line =  reader.readLine();
            numOfUsers = Integer.parseInt(line.trim());

        }catch  (Exception e) {
            e.printStackTrace();
        }

        return numOfUsers;
    }

    public static String createUser(String email) {
        Integer usersCount = Admin.countUsers();
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