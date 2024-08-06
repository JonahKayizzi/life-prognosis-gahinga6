package com.prognosis.cli;

import com.prognosis.cli.controller.PatientController;
import com.prognosis.cli.controller.UserController;

public class App {
    public static void main(String[] args) {
        App app = new App();
        app.welcomeMenu();
        // do {
            UserController userController = new UserController();
            PatientController patientController = new PatientController();

            patientController.viewProfile();

            // int choice = Integer.parseInt(System.console().readLine());
            // switch (choice) {
            //     case 1 -> userController.login();
            //     case 2 -> patientController.registerUser();
            //     case 3 -> System.exit(0);
            //     default -> System.out.println("Invalid choice");
            // }
        // } while (true);
    }

    private void welcomeMenu() {
        System.out.println("Welcome to Prognosis CLI");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
    }
}