package com.prognosis.cli;

import com.prognosis.cli.model.Admin;

public class App {
    public static void main(String[] args) {
       String code  =  Admin.createUser();
       String message = String.format("Use this code to register: %s", code);
       System.out.println(message);
    }
}