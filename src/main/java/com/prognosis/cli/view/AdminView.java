package com.prognosis.cli.view;

import java.util.Scanner;

import com.prognosis.cli.controller.UserController;

public class AdminView implements UserView {
    private final UserController userController = new UserController();
    

    @Override
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Register new user");
            System.out.println("2. Download Patient Information");
            System.out.println("3. Download Analysis Report");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> userController.createrUser();
                case 2 -> userController.downloadCSV();
                case 3 -> System.out.println("Downloading analysis report...");
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