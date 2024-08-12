package view;

import java.util.Scanner;

import controller.UserController;

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
                case 1 -> userController.viewProfile();
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

    @Override
    public void welcomeMenu() {
        System.out.println("Welcome Admin!");
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
