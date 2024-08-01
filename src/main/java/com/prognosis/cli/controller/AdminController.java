package com.prognosis.cli.controller;

//import required classes
import com.prognosis.cli.service.AdminService;
import com.prognosis.cli.view.AdminView;

public class AdminController {
    // instatiate AdminService and AdminView
    AdminService adminService = new AdminService();
    AdminView adminView = new AdminView();

    // createUser method
    public void createUser() {
        //call method to get user input from console
        String email = adminView.promptUserEmail();
        //call method to create user
        String code  = adminService.createUser(email);
        adminView.displayCode(code);
    }
}