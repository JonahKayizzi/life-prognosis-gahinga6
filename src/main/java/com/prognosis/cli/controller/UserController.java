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
        } else {
            System.out.println("Invalid credentials");
        }
    }

    public void createrUser() {
        String email = promptUserEmail();
        User createdPatient = userService.createPatient(email);
        if (createdPatient != null) {
            System.out.println(createdPatient.id);
            System.out.println(createdPatient.role);
            System.out.println(createdPatient.email + " created successfully");
            System.out.println("With code " + createdPatient.code);
        } else {
            System.out.println("User creation failed");
        }
    }

    public void registerPatientProfile() {
        String email = promptUserEmail();
        String code = promptCode();

        Patient verifiedPatient = userService.verifyPatient(email, code);
        if (verifiedPatient != null) {
            System.out.println("Your account has been verified successfully");
            System.out.println("Enter the following details");
            String password = promptUserPassword();
            verifiedPatient.password = password;
            String firstName = promptFirstName();
            verifiedPatient.firstName = firstName;
            String lastName = promptLastName();
            verifiedPatient.lastName = lastName;
            String dateOfBirth = promptDateOfBirth();
            verifiedPatient.dateOfBirth = dateOfBirth;
            String hivStatus = promptHivStatus();
            if (hivStatus.equalsIgnoreCase("yes")) {
                verifiedPatient.hivStatus = true;
                String dateOfDiagnosis = promptDateOfDiagnosis();
                verifiedPatient.dateOfDiagnosis = dateOfDiagnosis;
                String isOnART = promptIsOnART();
                if (isOnART.equalsIgnoreCase("yes")) {
                    verifiedPatient.isOnART = true;
                    String artStartDate = promptArtStartDate();
                    verifiedPatient.artStartDate = artStartDate;
                } else {
                    verifiedPatient.isOnART = false;
                }
            } else {
                verifiedPatient.hivStatus = false;
            }
            String country = promptCountry();
            verifiedPatient.country = country;
            userService.updatePatientDetails(verifiedPatient);
            PatientView patientView = new PatientView();
            patientView.displayMenu();
        } else {
            System.out.println("Verification failed, contact admin");
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

    public String promptFirstName() {
        System.out.println("Enter your first name:");
        return System.console().readLine();
    }

    public String promptLastName() {
        System.out.println("Enter your last name:");
        return System.console().readLine();
    }

    public String promptDateOfBirth() {
        System.out.println("Enter your date of birth:");
        return System.console().readLine();
    }

    public String promptHivStatus() {
        System.out.println("Are you HIV positive:");
        return System.console().readLine();
    }

    public String promptDateOfDiagnosis() {
        System.out.println("Enter your date of diagnosis:");
        return System.console().readLine();
    }

    public String promptIsOnART() {
        System.out.println("Are you taking anti retro viral drugs:");
        return System.console().readLine();
    }

    public String promptArtStartDate() {
        System.out.println("Enter the date you started taking  anti retro viral drugs:");
        return System.console().readLine();
    }

    public String promptCountry() {
        System.out.println("Enter your country of residence:");
        return System.console().readLine();
    }

}
