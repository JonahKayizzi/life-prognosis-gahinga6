package com.prognosis.cli.view;

public class UserView {

    public String promptUserEmail() {
        System.out.flush();
        System.out.println("Enter your email:");
        return System.console().readLine();
    }

    public String promptUserPassword() {
        System.out.flush();
        System.out.println("Enter your password:");
        return new String(System.console().readPassword());
    }

    public void displayAdminOptions() {
        System.out.flush();
        System.out.println("1. Register new user");
        System.out.println("2. Download Patient Information");
        System.out.println("3. Download Analysis Report");
        System.out.println("4. Exit");
    }

    public void displayUserOptions() {
        System.out.flush();
        System.out.println("1. View Profile");
        System.out.println("2. Update Profile");
        System.out.println("3. Download iCalendar");
        System.out.println("4. Exit");
    }

    public void displayErrorMessage(String message) {
        System.out.flush();
        System.out.println(message);
    }
}
