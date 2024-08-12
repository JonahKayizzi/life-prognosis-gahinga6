package com.prognosis.cli.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import com.prognosis.cli.model.Admin;
import com.prognosis.cli.model.Patient;
import com.prognosis.cli.model.Patient.HIVStatus;
import com.prognosis.cli.model.User;
import com.prognosis.cli.model.User.Role;
import com.prognosis.cli.utils.BashRunner;

public class UserService {

    // Create an instance of the BashRunner class
    private final BashRunner bashRunner = new BashRunner();
    // Implement the loginUser method

    private User initUser(String output){
        String[] parts = output.split(" ");
        String userId = parts[0];
        String role = parts[1];
        String email = parts[2];
        String code = parts[3];

        if(role.equalsIgnoreCase("admin")){
            return new Admin(userId, email, code);
        } 

        String password = parts[4];
        String firstName = parts[5];
        String lastName = parts[6];
        String dateOfBirth = parts[7];
        String hivStatus = parts[8];
        String country = parts[9];
        String dateOfDiagnosis = parts[10];
        String isOnArt = parts[11];
        String artStartDate = parts[12];

        // Check the role of the user
        if (role.equalsIgnoreCase("patient")) {
            return new Patient(userId, email, code, password, firstName, lastName, dateOfBirth, hivStatus, dateOfDiagnosis, isOnArt, artStartDate, country);
        } else {
            return null;
        }
    }

    public User loginUser(String username, String password) {
        try {
            // Execute the login_user method from the user_login.sh script
            String output = this.bashRunner.execute("user_login.sh", new String[]{username, password});
            if (output.equalsIgnoreCase("error")) {
                return null;
            } else {
                return this.initUser(output);
            }
        } catch (Exception e) {
            // Handle exception
            System.err.println("Error executing script: " + e.getMessage());
        }
        return null;
    }

    // Implement the createPatient method
    public Patient createPatient(String email) {
        // Get the number of users in the store
        Integer usersCount = this.countUsers();
        // Generate the next user ID by incrementing number of users
        String nextUserId = (++usersCount).toString();
        // Generate a random code for the user
        String code = UUID.randomUUID().toString();

        // Generate an array of arguments for the create_user.sh script
        String[] args = {nextUserId, Role.PATIENT.toString(), email, code};
        // Execute the create_user.sh script
        this.bashRunner.execute("create_user.sh", args);
        // Return a new Patient object
        return new Patient(nextUserId, email, code);
    }

    // Implement the verifyPatient method that verifies the patient and code given by the admin
    public Patient verifyPatient(String email, String code) {
        // Generate an array of arguments for the verify_user.sh script
        String[] args = {email, code};
        // Execute the verify_user.sh script
        String output = this.bashRunner.execute("verify_user.sh", args);
        // Check if the patient exists
        if (!output.equalsIgnoreCase("0")) {
            // Return a new Patient object
            return new Patient(output, email, code);
        }
        // if patient does not exist, return null
        return null;
    }

    // Implement the updatePatientDetails method
    public void updatePatientDetails(Patient patient) {
        // Generate an array of arguments for the update_patient.sh script
        String[] args = {patient.password, patient.firstName, patient.lastName, patient.dateOfBirth, patient.hivStatus.toString(), patient.dateOfDiagnosis, patient.isOnART, patient.artStartDate, patient.country, patient.id, patient.email, patient.code};
        // Execute the update_patient.sh script
        this.bashRunner.execute("register_patient.sh", args);
    }

    // Implement the countUsers method
    public Integer countUsers() {
        // Execute the count_users.sh script to get the number of users in the user store
        String output = this.bashRunner.execute("count_users.sh", null);
        // convert result to integer
        Integer numOfUsers = Integer.valueOf(output.trim());
        // return the number of users
        return numOfUsers;
    }

    // Implement the logout method
    public void logout() {
        // Execute the logout.sh script to empty the session storage
        this.bashRunner.execute("logout.sh", null);
    }

    public User getProfile() {
        String output = this.bashRunner.execute("get_profile.sh", null);
        User user = this.initUser(output);
        if (user instanceof Patient patient) {
            Float remainingLifeSpan = this.calculateLifeSpan(patient);
            patient.remainingLifeSpan = remainingLifeSpan;

            return patient;
        }

        return user;
    }

    private Float getLifeExpectancy(String country) {
        String[] args = {country};
        String expectedLifeExpectancyInString = this.bashRunner.execute("get-country-life-expectancy.sh", args);
        Float expectedLifeExpectancy = Float.parseFloat(expectedLifeExpectancyInString);
        return expectedLifeExpectancy;
    }

    private Float calculateTimeSince(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate parsedDate = LocalDate.parse(time, formatter);
        LocalDate now = LocalDate.now();

        return (float) Period.between(parsedDate, now).getYears();
    }

    private Integer calculateTimeBetween(String startTime, String endTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate parsedStartDate = LocalDate.parse(startTime, formatter);
        LocalDate parsedEndDate = LocalDate.parse(endTime, formatter);

        return Period.between(parsedStartDate, parsedEndDate).getYears();
    }

    public Float calculateLifeSpan(Patient patient) {
        Float expectedLifeExpectancy = this.getLifeExpectancy(patient.country);
        Float remainingTime = expectedLifeExpectancy - this.calculateTimeSince(patient.dateOfBirth);

        if (patient.hivStatus == HIVStatus.NEGATIVE) {
            return remainingTime;
        }

        if (patient.isOnART == "false") {
            Float calculateTimeAfterDiagnosis = this.calculateTimeSince(patient.dateOfDiagnosis);
            return Math.min(remainingTime, 5 - calculateTimeAfterDiagnosis);
        }

        Integer timeSince = this.calculateTimeBetween(patient.dateOfDiagnosis, patient.artStartDate);

        return (float) (remainingTime * Math.pow(0.9, 1 + timeSince));
    }

    public Boolean checkIfCountryCodeIsValid(String countryCode){
        String[] args =  { countryCode };
        String output = this.bashRunner.execute("check_if_country_exists.sh", args);

        return output.trim().equals("1");
    }

    public User exportDataToCSV() {
        try {
            // Execute the exportDataToCSV method from the user_login.sh script
            String output = this.bashRunner.execute("data-store-csv.sh", null);
        } catch (Exception e) {
            // Handle exception
            System.err.println("Error executing script: " + e.getMessage());
        }
        return null;
    }

}
