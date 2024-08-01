package com.prognosis.cli;

import java.util.Scanner;

import com.prognosis.cli.model.Admin;
import com.prognosis.cli.service.AdminService;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user's email address:");
        String email = scanner.nextLine();
        scanner.close();

        AdminService adminService =  new AdminService();

        Integer number = adminService.countUsers();

        // String message = String.format("Use this code to register: %s", code);
        System.out.println(number);
    }
}