package com.prognosis.cli.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.prognosis.cli.model.Admin;
import com.prognosis.cli.model.Patient;
import com.prognosis.cli.model.User;
import com.prognosis.cli.model.Patient.HIVStatus;
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

    public void createrUser() {
        String email = promptUserEmail();
        User createdPatient = userService.createPatient(email);
        if (createdPatient != null) {
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
            String firstName = promptFirstName();
            verifiedPatient.firstName = firstName;
            String lastName = promptLastName();
            verifiedPatient.lastName = lastName;
            String dateOfBirth = promptDateOfBirth();
            verifiedPatient.dateOfBirth = dateOfBirth;
            HIVStatus hivStatus = promptHivStatus();
            if (HIVStatus.POSITIVE == hivStatus) {
                verifiedPatient.hivStatus = hivStatus;
                String dateOfDiagnosis = promptDateOfDiagnosis();
                verifiedPatient.dateOfDiagnosis = dateOfDiagnosis;
                String isOnART = promptIsOnART();
                verifiedPatient.isOnART = isOnART; 
                if (isOnART.equalsIgnoreCase("yes")) {
                    String artStartDate = promptArtStartDate();
                    verifiedPatient.artStartDate = artStartDate;
                }
            }
            String country = promptCountry();
            verifiedPatient.country = country;
            userService.updatePatientDetails(verifiedPatient);
            PatientView patientView = new PatientView();
            patientView.displayMenu();
        }else {
            System.out.println("Verification failed, contact admin");
        }
    }

    public String promptUserEmail() {
        String email = null;
        
        System.out.println("Enter your email:");
        Boolean isValid = false;
        do {
            email =  System.console().readLine();
            String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
            Pattern pattern = Pattern.compile(regex);

            Matcher matcher = pattern.matcher(email);
            isValid = matcher.matches();

            if(!isValid){
                System.out.println("Validation failed. Please use a proper email address");
            }
        } while (email == null); 

        return email;
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
        String dateString = "";
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("MM/dd/yyyy");

        System.out.println("Enter your date of birth:");
        Boolean isValid = false;
        do {
            try {
                dateString =  System.console().readLine();
                LocalDate parsedDate = LocalDate.parse(dateString, formatter);

                if(parsedDate.isAfter(LocalDate.now())) {
                    System.out.println("Validation failed. Birthdate should be in the past.");
                }else{
                    isValid = true;
                }
            } catch (DateTimeParseException e){
                System.out.println("Validation failed. Please use \"MM/dd/yyyy\" format");
            }
        } while (!isValid); 


        return dateString;
    }

    public HIVStatus promptHivStatus() {
        System.out.println("Are you HIV positive: (Yes/No)");
        HIVStatus hivStatus = null;
        
        do {
            String hivStatusString = System.console().readLine();
            if(hivStatusString.toLowerCase().equals("yes")) {
                hivStatus = HIVStatus.POSITIVE;
            } else if (hivStatusString.toLowerCase().equals("no")) {
                hivStatus = HIVStatus.NEGATIVE;
            } else {
                System.out.println("Validation failed. Please enter \"Yes\" or \"No\"");
            }
        } while (hivStatus == null); 

        return hivStatus;
    }

    public String promptDateOfDiagnosis() {
        String dateString = "";
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("MM/dd/yyyy");
        
        System.out.println("Enter your date of diagnosis:");
        Boolean isValid = false;
        do {
            try {
                dateString =  System.console().readLine();
                LocalDate parsedDate = LocalDate.parse(dateString, formatter);

                if(parsedDate.isAfter(LocalDate.now())) {
                    System.out.println("Validation failed. Date of diagnosis should be in the past.");
                }else{
                    isValid = true;
                }
            } catch (DateTimeParseException e){
                System.out.println("Validation failed. Please use \"MM/dd/yyyy\" format");
            }
        } while (!isValid); 


        return dateString;
    }


    public String promptIsOnART() {
        System.out.println("Are you taking anti retro viral drugs: (Yes/No)");
        String isOnArt = null;
        
        do {
            String isOnArtString = System.console().readLine().toLowerCase();
            if(isOnArtString.equals("yes")) {
                isOnArt = isOnArtString;
            } else if (isOnArtString.equals("no")) {
                isOnArt = isOnArtString;
            } else {
                System.out.println("Validation failed. Please enter \"Yes\" or \"No\"");
            }
        } while (isOnArt == null); 

        return isOnArt;
    }

    public String promptArtStartDate() {
        String dateString = "";
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("MM/dd/yyyy");
        
        System.out.println("Enter the date you started taking  anti retro viral drugs:");
        Boolean isValid = false;
        do {
            try {
                dateString =  System.console().readLine();
                LocalDate parsedDate = LocalDate.parse(dateString, formatter);

                if(parsedDate.isAfter(LocalDate.now())) {
                    System.out.println("Validation failed. Date should be in the past.");
                }else{
                    isValid = true;
                }
            } catch (DateTimeParseException e){
                System.out.println("Validation failed. Please use \"MM/dd/yyyy\" format");
            }
        } while (!isValid); 


        return dateString;
    }

    public String promptCountry() {
        System.out.println("Enter your country of residence:");
        return System.console().readLine();
    }

}