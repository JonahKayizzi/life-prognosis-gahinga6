package com.prognosis.cli.view;

import java.util.Scanner;

public class PatientView implements UserView {

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
                case 2 -> System.out.println("Updating profile...");
                case 3 -> System.out.println("Downloading iCalendar...");
                case 4 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    public String promptFirstName() {
        System.out.println("Enter your first name:");
        return System.console().readLine();
    }

    public String promptLastName() {
        System.out.println("Enter your last name:");
        return System.console().readLine();
    }

    public String promptCode() {
        System.out.println("Enter your code:");
        return System.console().readLine();
    }

    public String promptDateOfBirth() {
        System.out.println("Enter your date of birth:");
        return System.console().readLine();
    }

    public String promptHivStatus() {
        System.out.println("Enter your HIV status:");
        return System.console().readLine();
    }

    public String promptDateOfDiagnosis() {
        System.out.println("Enter your date of diagnosis:");
        return System.console().readLine();
    }


    public String promptIsOnART() {
        System.out.println("Are you taking anti retro viral drugs:");
        return System.console().readLine();
    }

    public String promptArtStartDate() {
        System.out.println("Enter the date you started taking  anti retro viral drugs:");
        return System.console().readLine();
    }

    public String promptCountry() {
        System.out.println("Enter your country of residence:");
        return System.console().readLine();
    }

    public void displayCode(String code) {
        System.out.println(String.format("Use this code to register: %s", code));
    }

    @Override
    public void displayErrorMessage(String message) {
        System.out.println(message);
    }
}
