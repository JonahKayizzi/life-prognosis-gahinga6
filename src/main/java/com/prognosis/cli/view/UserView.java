package com.prognosis.cli.view;

import com.prognosis.cli.controller.AdminController;
import com.prognosis.cli.controller.PatientController;
import com.prognosis.cli.controller.UserController;

public class UserView {
    public String promptUserEmail() {
        System.out.println("Enter your email:");
        return System.console().readLine();
    }

    public String promptUserPassword() {
        System.out.println("Enter your password:");
        return new String(System.console().readPassword());
    }

    public void displayAdminOptions() {
        System.out.println("1. Register new user");
        // System.out.println("2. Download Patient Information");
        // System.out.println("3. Download Analysis Report");
        System.out.println("2. Exit");

        
        do {
            AdminController adminController = new AdminController();
            int choice = Integer.parseInt(System.console().readLine());
            
            switch (choice) {
                case 1 -> adminController.createUser();
                case 2 -> System.exit(0);
                default -> System.out.println("Invalid choice");
            }
            
            this.displayUserOptions();
        } while (true);
    }

    public void displayUserOptions() {
        System.out.println("1. View Profile");
        // System.out.println("2. Update Profile");
        // System.out.println("3. Download iCalendar");
        System.out.println("2. Exit");
    }

    public void displayErrorMessage(String message) {
        System.out.println(message);
    }
}