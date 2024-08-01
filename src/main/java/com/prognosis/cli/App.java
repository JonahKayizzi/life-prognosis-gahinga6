package com.prognosis.cli;

import java.util.Scanner;

import com.prognosis.cli.controller.UserController;
import com.prognosis.cli.service.AdminService;

public class App {
    public static void main(String[] args) {
        do {
            App app = new App();
            app.welcomeMenu();

            UserController userController = new UserController();
            int choice = Integer.parseInt(System.console().readLine());
            switch (choice) {
                case 1:
                    userController.login();
                    break;
                case 2:
                   // app.register();
                   Scanner scanner = new Scanner(System.in);
                    System.out.println("Enter user's email address:");
                    scanner.close();

                    AdminService adminService =  new AdminService();

                    Integer number = adminService.countUsers();

                    // String message = String.format("Use this code to register: %s", code);
                    System.out.println(number);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
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