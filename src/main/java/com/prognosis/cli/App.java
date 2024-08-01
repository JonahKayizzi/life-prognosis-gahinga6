package com.prognosis.cli;

import com.prognosis.cli.utils.BashRunner;

public class App {
    public static void main(String[] args) {
        do {
            App app = new App();
            app.welcomeMenu();

            UserController userController = new UserController();
            AdminController adminController = new AdminController();

            private final BashRunner bashRunner = new BashRunner();

            this.bashRunner.execute("create_a   dmin.sh", null);
            int choice = Integer.parseInt(System.console().readLine());
            switch (choice) {
                case 1 -> userController.login();
                case 2 -> adminController.register();
                case 3 -> System.exit(0);
                default -> System.out.println("Invalid choice");
            }
        } while (true);
    }

    private void welcomeMenu() {
        System.out.println("Welcome to Prognosis CLI");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
    }
}