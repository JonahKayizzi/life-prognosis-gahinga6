package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Admin;
import model.Patient;
import model.Patient.HIVStatus;
import model.User;
import service.UserService;
import view.AdminView;
import view.PatientView;
import view.UserView;

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
            System.out.println("Logged in as: " + loggedUser.email);
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
            this.logout();
        }
    }

    // Implement the createrUser method
    public void createrUser() {
        String email = promptCreationEmail();
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
            HIVStatus hivStatus = promptHivStatus();
            verifiedPatient.hivStatus = hivStatus;
            verifiedPatient.dateOfDiagnosis = null;
            verifiedPatient.isOnART = "no";
            verifiedPatient.artStartDate = null;

            if (HIVStatus.POSITIVE == hivStatus) {
                String dateOfDiagnosis = promptDateOfDiagnosis();
                verifiedPatient.dateOfDiagnosis = dateOfDiagnosis;
                String isOnART = promptIsOnART();
                verifiedPatient.isOnART = isOnART;
                // Check if the patient is on ART and prompt for more details
                if (isOnART.equalsIgnoreCase("yes")) {
                    String artStartDate = promptArtStartDate();
                    verifiedPatient.artStartDate = artStartDate;
                }
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
            this.logout();
        }
    }

    // Implement the updateProfile method
    public void updateProfile() {
        User userToUpdate = userService.getProfile();
        Patient patientToUpdate = (Patient) userToUpdate;
        // Prompt the user for other details to be updated
        patientToUpdate.password = updatePassword(patientToUpdate.password);
        patientToUpdate.firstName = updateField("first name", patientToUpdate.firstName);
        patientToUpdate.lastName = updateField("last name", patientToUpdate.lastName);
        patientToUpdate.dateOfBirth = updateField("date of birth", patientToUpdate.dateOfBirth);
        if (updateField("HIV status", patientToUpdate.hivStatus.toString()).toLowerCase().equals("yes")) {
            patientToUpdate.hivStatus = HIVStatus.POSITIVE;
        } else {
            patientToUpdate.hivStatus = HIVStatus.NEGATIVE;
        }
        patientToUpdate.dateOfDiagnosis = updateField("date of diagnosis", patientToUpdate.dateOfDiagnosis);
        patientToUpdate.isOnART = updateField("is on ART", patientToUpdate.isOnART);
        patientToUpdate.artStartDate = updateField("ART start date", patientToUpdate.artStartDate);
        patientToUpdate.country = updateField("country", patientToUpdate.country);
        userService.updatePatientDetails(patientToUpdate);
    }

    public String updateField(String field, String value) {
        // Prompt the user for other details to be updated
        System.out.println("Enter new " + field + " if you wish to change from: " + value + "(Type 'next' if none) : ");
        String response = System.console().readLine();
        if (response.equalsIgnoreCase("next")) {
            return value;
        } else {
            return response;
        }
    }

    public String updatePassword(String value) {
        // Prompt the user for other details to be updated
        System.out.println("Enter new password if you wish to change from: ***** (Type 'next' if none) : ");
        String response = System.console().readLine();
        if (response.equalsIgnoreCase("next")) {
            return value;
        } else {
            return response;
        }
    }

    public String promptUserEmail() {
        String email = null;

        System.out.println("Enter your email:");
        Boolean isValid = false;
        do {
            String tempEmail = System.console().readLine();
            String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
            Pattern pattern = Pattern.compile(regex);

            Matcher matcher = pattern.matcher(tempEmail);
            isValid = matcher.matches();

            if (isValid) {
                email = tempEmail;
            } else {
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        System.out.println("Enter your date of birth (MM/dd/yyyy):");
        Boolean isValid = false;
        do {
            try {
                dateString = System.console().readLine();
                LocalDate parsedDate = LocalDate.parse(dateString, formatter);

                if (parsedDate.isAfter(LocalDate.now())) {
                    System.out.println("Validation failed. Birthdate should be in the past.");
                } else {
                    isValid = true;
                }
            } catch (DateTimeParseException e) {
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
            if (hivStatusString.toLowerCase().equals("yes")) {
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        System.out.println("Enter your date of diagnosis (MM/dd/yyyy):");
        Boolean isValid = false;
        do {
            try {
                dateString = System.console().readLine();
                LocalDate parsedDate = LocalDate.parse(dateString, formatter);

                if (parsedDate.isAfter(LocalDate.now())) {
                    System.out.println("Validation failed. Date of diagnosis should be in the past.");
                } else {
                    isValid = true;
                }
            } catch (DateTimeParseException e) {
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
            if (isOnArtString.equals("yes")) {
                isOnArt = isOnArtString;
            } else if (isOnArtString.equals("no")) {
                isOnArt = isOnArtString;
            } else {
                System.out.println("Validation failed. Please enter \"Yes\" or \"No\"");
            }
        } while (isOnArt == null);

        return isOnArt;
    }

    public String promptCreationEmail(){
        UserView userView = new AdminView();
        do { 
            String email = promptUserEmail();
            if(userService.checkIfEmailExists(email)){
                System.out.println("Email already exists. Please use another email");
            } else {
                return email;
            }
            userView.optOutMenu();
        } while (true);
    }

    public String promptArtStartDate() {
        String dateString = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        System.out.println("Enter the date you started taking  anti retro viral drugs (MM/dd/yyyy):");
        Boolean isValid = false;
        do {
            try {
                dateString = System.console().readLine();
                LocalDate parsedDate = LocalDate.parse(dateString, formatter);

                if (parsedDate.isAfter(LocalDate.now())) {
                    System.out.println("Validation failed. Date should be in the past.");
                } else {
                    isValid = true;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Validation failed. Please use \"MM/dd/yyyy\" format");
            }
        } while (!isValid);

        return dateString;
    }

    public String promptCountry() {
        System.out.println("Enter your country's code");

        String country = "";
        
        Boolean isValid = false;
        do {
            String tempCountry =  System.console().readLine();

            if(tempCountry.length() != 3) {
                System.out.println("Validation failed. Country code should be 3 characters");
            } else if(!userService.checkIfCountryCodeIsValid(tempCountry)){
                System.out.println("Validation failed. Not a valid country code");
            } else {
                country = tempCountry;
                isValid = true;
            }
        } while (!isValid); 

        return country;

    }

    public void viewProfile() {
        User user = userService.getProfile();

        System.out.println(String.format("First name ----------- %s", user.firstName));
        System.out.println(String.format("Last name ----------- %s", user.lastName));
        System.out.println(String.format("Email ----------- %s", user.email));

        if (user instanceof Patient) {
            Patient patient = (Patient) user;
            System.out.println(String.format("Date of birth ----------- %s", patient.dateOfBirth));
            System.out.println(String.format("HIV status ----------- %s", patient.hivStatus));
            System.out.println(String.format("Date of diagnosis ----------- %s", patient.dateOfDiagnosis));
            System.out.println(String.format("is on ART ----------- %s", patient.isOnART));
            System.out.println(String.format("ART start date ----------- %s", patient.artStartDate));
            System.out.println(String.format("Country ----------- %s", patient.country));
            System.out.println(String.format("Expected date of death ----------- %s", patient.dateOfDeath));
        }
    }

    public void downloadCSV() {
        System.out.println("downloading.....");
        userService.exportDataToCSV();
        System.out.println("downloaded successfully");
        UserView userView = new AdminView();
        userView.optOutMenu();
    }

    public void initialize() {
        UserView userView = new AdminView();
        userView.welcomeMenu();
    }

    public void logout() {
        System.out.println("Logging out...");
        userService.logout();
        initialize();
    }

    public void terminate() {
        System.out.println("Exiting...");
        userService.logout();
        System.exit(0);
    }

    public void downloadCalendar() {
        System.out.println("downloading.....");
        User user = userService.getProfile();
        Patient patient = (Patient) user;
        userService.exportDataToCalendar(patient);
        System.out.println("downloaded successfully");
    }
    public void exportAnalytics() {
        System.out.println("Exporting.....");
        userService.exportAnalytics();
        System.out.println("exporting successfully");
    }


}
