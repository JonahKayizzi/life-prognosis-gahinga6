package com.prognosis.cli.controller;

import com.prognosis.cli.model.User;
import com.prognosis.cli.view.UserView;

public class UserController {
    
    private User userModel;

    private UserView userView() {
        return new UserView();
    }

    public void login() {  
        String email = userView().promptUserEmail();
        String password = userView().promptUserPassword();

         // Validate user input
         if ("admin".equals(userModel.loginUser(email, password))) {
            // Login successful, display welcome message
            userView().displayAdminOptions();
        } else if ("error".equals(userModel.loginUser(email, password))) {
            // Login failed, display error message
            userView().displayErrorMessage("Invalid credentials");
        } else {
            // Login successful, display welcome message
            userView().displayUserOptions();
        }

    }



}