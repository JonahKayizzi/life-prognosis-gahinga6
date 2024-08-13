package view;

import controller.UserController;
import java.util.Scanner;

public class PatientView implements UserView {
    UserController userController = new UserController();

    @Override
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            userController.clearConsole();
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println("::  Logged in as Patient !                          ::");
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println("::  1. View Profile                                 ::");
            System.out.println("::  2. Update Profile                               ::");
            System.out.println("::  3. Download iCalendar                           ::");
            System.out.println("::  4. Exit                                         ::");
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println("");
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> userController.viewProfile();
                case 2 -> userController.updateProfile();
                case 3 -> userController.downloadCalendar();
                case 4 -> userController.logout();
                default -> System.out.println("Invalid choice");
            }
        }
    }

    @Override
    public void optOutMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("3. Back");
            System.out.println("4. Logout");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 3 -> displayMenu();
                case 4 -> userController.logout();
                case 5 -> userController.terminate();
                default -> System.out.println("Invalid choice");
            }
        }
    }


    @Override
    public void displayErrorMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void welcomeMenu() {
        System.out.println("Welcome Welcome To Life Prognosis and Management Tool!");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        do {
            UserController userController = new UserController();
            int choice = Integer.parseInt(System.console().readLine());
            switch (choice) {
                case 1 -> userController.handleLogin();
                case 2 -> userController.registerPatientProfile();
                case 3 -> userController.logout();
                default -> System.out.println("Invalid choice");
            }
        } while (true);
    }
}
