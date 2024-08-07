package com.prognosis.cli.controller;

import com.prognosis.cli.model.Admin;
import com.prognosis.cli.model.Patient;
import com.prognosis.cli.model.User;
import com.prognosis.cli.service.UserService;
import com.prognosis.cli.view.AdminView;
import com.prognosis.cli.view.PatientView;
import com.prognosis.cli.view.UserView;

public class UserController {

    // Create an instance of the UserService class
    private final UserService userService = new UserService();

    // Implement the handleLogin method
    public void handleLogin() {
        // Prompt the user for email and password
        String email = promptUserEmail();
        String password = promptUserPassword();
        // Call the loginUser method from the UserService class
        User loggedUser = userService.loginUser(email, password);

        // Check if the user exists
        if (loggedUser != null) {
            // Prepare an instance of the UserView for displaying the menu
            UserView userView;
            // Check the role of the loggedin user
            if (loggedUser instanceof Admin) {
                userView = new AdminView();
                //Display the admin menu if the user is an admin
                userView.displayMenu();
            } else if (loggedUser instanceof Patient) {
                userView = new PatientView();
                //Display the patient menu if the user is a patient
                userView.displayMenu();

            }
        } else {
            // Display an error message if the user does not exist
            System.out.println("Invalid credentials");
        }
    }

    // Implement the createrUser method
    public void createrUser() {
        // Prompt the user for email
        String email = promptUserEmail();
        // Call the createPatient method from the UserService class
        User createdPatient = userService.createPatient(email);
        // Check if the user was created successfully
        if (createdPatient != null) {
            // Display the user email and code
            System.out.println(createdPatient.email + " created successfully");
            System.out.println("With code " + createdPatient.code);
        } else {
            // Display an error message if the user creation failed
            System.out.println("User creation failed");
        }
    }

    // Implement the registerPatientProfile method
    public void registerPatientProfile() {
        // Prompt the user for email and code
        String email = promptUserEmail();
        String code = promptCode();

        // Call the verifyPatient method from the UserService class
        Patient verifiedPatient = userService.verifyPatient(email, code);
        // Check if the patient was verified successfully
        if (verifiedPatient != null) {
            // Prompt the user for other details
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
            // Check if the patient is HIV positive and prompt for more details
            if (hivStatus.equalsIgnoreCase("yes")) {
                verifiedPatient.hivStatus = true;
                String dateOfDiagnosis = promptDateOfDiagnosis();
                verifiedPatient.dateOfDiagnosis = dateOfDiagnosis;
                String isOnART = promptIsOnART();
                // Check if the patient is on ART and prompt for more details
                if (isOnART.equalsIgnoreCase("yes")) {
                    verifiedPatient.isOnART = true;
                    String artStartDate = promptArtStartDate();
                    verifiedPatient.artStartDate = artStartDate;
                } else {
                    // Set the patient to not be on ART if the patient is not taking ART and other details to null
                    verifiedPatient.isOnART = false;
                    verifiedPatient.artStartDate = null;
                }
            } else {
                // Set the HIV status to false if the patient is not HIV positive and other details to null
                verifiedPatient.hivStatus = false;
                verifiedPatient.dateOfDiagnosis = null;
                verifiedPatient.isOnART = false;
                verifiedPatient.artStartDate = null;
            }
            String country = promptCountry();
            verifiedPatient.country = country;

            // Call the updatePatientDetails method from the UserService class
            userService.updatePatientDetails(verifiedPatient);
            PatientView patientView = new PatientView();
            // Display the patient menu
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

    public void logout() {
        System.out.println("Exiting...");
        userService.logout();
        System.exit(0);
    }

}
