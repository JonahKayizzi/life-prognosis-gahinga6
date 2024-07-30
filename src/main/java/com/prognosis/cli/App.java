package com.prognosis.cli;

import com.prognosis.cli.model.Admin;

public class App {
    public static void main(String[] args) {
       String code  =  Admin.createUser("John");
       System.out.println(code);  
    }
}