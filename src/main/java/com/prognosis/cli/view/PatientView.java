package com.prognosis.cli.view;

import java.util.Scanner;

import com.prognosis.cli.controller.UserController;

public class PatientView implements UserView {
    UserController userController = new UserController();

    @Override
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. View Profile");
            System.out.println("2. Update Profile");
            System.out.println("3. Download iCalendar");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> System.out.println("Viewing profile...");
                case 2 -> userController.updateProfile();
                case 3 -> System.out.println("Downloading iCalendar...");
                case 4 -> userController.logout();
                default -> System.out.println("Invalid choice");
            }
        }
    }

    @Override
    public void displayErrorMessage(String message) {
        System.out.println(message);
    }
}
