package com.prognosis.cli.controller;

import com.prognosis.cli.service.UserService;
import com.prognosis.cli.view.UserView;

public class UserController {
    
    UserService userService = new UserService();

    private UserView userView() {
        return new UserView();
    }

    public void login() {  
        String email = userView().promptUserEmail();
        String password = userView().promptUserPassword();
        String loginStatus = userService.loginUser(email, password);
         // Validate user input
        switch (loginStatus) {
            case "admin":
                 // Login successful, display welcome message
                userView().displayAdminOptions();
                break;
            case "error":
                // Login failed, display error message
                userView().displayErrorMessage("Invalid credentials");
                break;
            default:
                // Login successful, display welcome message
                userView().displayUserOptions();
                break;
        }


    }



}