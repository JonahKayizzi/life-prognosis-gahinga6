package com.prognosis.cli.view;

public class AdminView {
    public String promptUserEmail() {
        System.out.println("Enter your email:");
        return System.console().readLine();
    }


    public void displayCode(String code) {
        System.out.println(String.format("Use this code to register: %s", code));
    }


}