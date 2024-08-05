package com.prognosis.cli.controller;

import com.prognosis.cli.model.Admin;
import com.prognosis.cli.model.Patient;
import com.prognosis.cli.model.User;
import com.prognosis.cli.service.UserService;
import com.prognosis.cli.view.AdminView;
import com.prognosis.cli.view.PatientView;
import com.prognosis.cli.view.UserView;

public class UserController {
    
    private final UserService userService = new UserService();

    public void handleLogin() {  
        String email = promptUserEmail();
        String password = promptUserPassword();
        User loggedUser = userService.loginUser(email, password);

        if (loggedUser != null) {
            UserView userView;
            if (loggedUser instanceof Admin) {
                userView = new AdminView();
                userView.displayMenu();
            } else if (loggedUser instanceof Patient) {
                userView = new PatientView();
                userView.displayMenu();
            }
        }else {
            System.out.println("Invalid credentials");
        }
    }

    public void registerUser() {
        String email = promptUserEmail();
        User createdPatient = userService.createPatient(email);
        if (createdPatient != null) {
            System.out.println(createdPatient.email + " created successfully");
            System.out.println("With code " + createdPatient.code);
        } else {
            System.out.println("User creation failed");
        }
    }

    public String promptUserEmail() {
        System.out.println("Enter your email:");
        return System.console().readLine();
    }

    public String promptUserPassword() {
        System.out.println("Enter your password:");
        return new String(System.console().readPassword());
    }

    public String promptCode() {
        System.out.println("Enter your code:");
        return System.console().readLine();
    }

}