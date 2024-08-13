package view;

import controller.UserController;
import java.util.Scanner;

public class AdminView implements UserView {

    private final UserController userController = new UserController();

    @Override
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            userController.clearConsole();
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println("::  Logged in as Admin !                            ::");
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println("::  1. Register new user                            ::");
            System.out.println("::  2. Download Patient Information                 ::");
            System.out.println("::  3. Download Analysis Report                     ::");
            System.out.println("::  4. Logout                                       ::");
            System.out.println("::  5. Exit                                         ::");
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println("");
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> userController.createrUser();
                case 2 -> userController.downloadCSV();
                case 3 -> userController.exportAnalytics();
                case 4 -> userController.logout();
                default -> System.out.println("Invalid choice");
            }
        }
    }

    @Override
    public void optOutMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Back");
            System.out.println("2. Logout");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 ->
                    displayMenu();
                case 2 ->
                    userController.logout();
                case 3 ->
                    userController.terminate();
                default ->
                    System.out.println("Invalid choice");
            }
        }
    }

    @Override
    public void displayErrorMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void welcomeMenu() {
        System.out.println("");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("::  Welcome To Life Prognosis and Management Tool!  ::");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("::                      1. Login                    ::");  
        System.out.println("::                      2. Register                 ::");
        System.out.println("::                      3. Exit                     ::");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("");
        System.out.println("Enter your choice: ");
        do {
            UserController userController = new UserController();
            int choice = Integer.parseInt(System.console().readLine());
            switch (choice) {
                case 1 ->
                    userController.handleLogin();
                case 2 ->
                    userController.registerPatientProfile();
                case 3 ->
                    userController.terminate();
                default ->
                    System.out.println("Invalid choice");
            }
        } while (true);
    }
}
