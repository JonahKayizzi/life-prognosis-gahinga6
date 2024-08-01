package com.prognosis.cli.controller;

import com.prognosis.cli.service.AdminService;
import com.prognosis.cli.view.AdminView;

public class AdminController {
    AdminService adminService = new AdminService();
    AdminView adminView = new AdminView();


    public void createUser() {
        String email = adminView.promptUserEmail();
        String code  = adminService.createUser(email);
        adminView.displayCode(code);
    }
}