package com.prognosis.cli.service;

import java.util.UUID;

import com.prognosis.cli.model.Admin;
import com.prognosis.cli.model.Patient;
import com.prognosis.cli.model.User;
import com.prognosis.cli.model.User.Role;
import com.prognosis.cli.utils.BashRunner;

public class UserService {
    // Create an instance of the BashRunner class
    private final BashRunner bashRunner = new BashRunner();
    // Implement the loginUser method
    public User loginUser(String email, String password) {
        try {
            // Execute the login_user method from the user_login.sh script
            String output = this.bashRunner.execute("user_login.sh", new String[]{email, password});

            // Split the output into an array of strings
            String[] parts = output.split(" ");
            String userId = parts[0];
            String role = parts[1];
            String code = parts[3];

            // Check the role of the user
            if (role.equalsIgnoreCase("admin")) {
                return new Admin(userId, email, code);
            } else if (role.equalsIgnoreCase("patient")) {
                return new Patient(userId, email, code);
            } else {
                return null;
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
        String[] args = { nextUserId, Role.PATIENT.toString(), email, code };
        // Execute the create_user.sh script
        this.bashRunner.execute("create_user.sh", args);
        // Return a new Patient object
        return new Patient(nextUserId, email, code);
    }

    // Implement the verifyPatient method that verifies the patient and code given by the admin
    public Patient verifyPatient(String email, String code) {
        // Generate an array of arguments for the verify_user.sh script
        String[] args = { email, code };
        // Execute the verify_user.sh script
        String output = this.bashRunner.execute("verify_user.sh", args);
        // Check if the patient exists
        if (output != null){
            // Return a new Patient object
            return new Patient(output, email, code);
        }
        // if patient does not exist, return null
        return null;
    }

    // Implement the updatePatientDetails method
    public void updatePatientDetails(Patient patient) {
        // Generate an array of arguments for the update_patient.sh script
        String[] args = { patient.password, patient.firstName, patient.lastName, patient.dateOfBirth, patient.hivStatus.toString(), patient.dateOfDiagnosis, patient.isOnART.toString(), patient.artStartDate, patient.country, patient.id };
        // Execute the update_patient.sh script
        this.bashRunner.execute("register_patient.sh", args);
    }

    // Implement the countUsers method
    public Integer countUsers(){
        // Execute the count_users.sh script to get the number of users in the user store
        String output = this.bashRunner.execute("count_users.sh", null);
        // convert result to integer
        Integer numOfUsers = Integer.valueOf(output.trim());
        // return the number of users
        return numOfUsers;
      }

    // Implement the logout method
    public void logout(){
        // Execute the logout.sh script to empty the session storage
        this.bashRunner.execute("logout.sh", null);
    }
}