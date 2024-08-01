package com.prognosis.cli.controller;

import com.prognosis.cli.service.AdminService;
import com.prognosis.cli.view.UserView;

public class AdminController {
    
    AdminService adminService = new AdminService();

    private UserView userView() {
        return new UserView();
    }

    public void register() {
        String email = userView().promptUserEmail();
        Integer number = adminService.countUsers();
        System.out.println(number);
    }
}