package com.prognosis.cli;

import com.prognosis.cli.controller.AdminController;
import com.prognosis.cli.controller.PatientController;
import com.prognosis.cli.controller.UserController;
import com.prognosis.cli.utils.BashRunner;

public class App {
    public static void main(String[] args) {
        do {
            App app = new App();
            app.welcomeMenu();

            UserController userController = new UserController();
            AdminController adminController = new AdminController();
            PatientController patientController = new PatientController();

            final BashRunner bashRunner = new BashRunner();

            // bashRunner.execute("create_admin.sh", null);
            int choice = Integer.parseInt(System.console().readLine());
            switch (choice) {
                case 1 -> userController.login();
                case 2 -> adminController.createUser();
                case 3 -> patientController.registerUser();
                case 4 -> System.exit(0);
                default -> System.out.println("Invalid choice");
            }
        } while (true);
    }

    private void welcomeMenu() {
        System.out.println("Welcome to Prognosis CLI");
        System.out.println("1. Login");
        System.out.println("2. Create User");
        System.out.println("3. Register");
        System.out.println("4. Exit");
    }
}