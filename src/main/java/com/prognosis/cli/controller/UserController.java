package com.prognosis.cli.controller;

import com.prognosis.cli.model.Admin;
import com.prognosis.cli.model.Patient;
import com.prognosis.cli.model.User;
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
        User loggedUser = userService.loginUser(email, password);

        if (loggedUser != null) {
            if (loggedUser instanceof Admin) {
                userView().displayAdminOptions();
            } else if (loggedUser instanceof Patient) {
                userView().displayUserOptions();
            }
        }else {
            userView().displayErrorMessage("Invalid email or password.");
        }
    }



}