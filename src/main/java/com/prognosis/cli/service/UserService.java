package com.prognosis.cli.service;

import java.util.UUID;

import com.prognosis.cli.model.Admin;
import com.prognosis.cli.model.Patient;
import com.prognosis.cli.model.User;
import com.prognosis.cli.model.User.Role;
import com.prognosis.cli.utils.BashRunner;

public class UserService {
    private final BashRunner bashRunner = new BashRunner();
    public User loginUser(String username, String password) {
        // Implement actual user validation logic
        try {
            // Execute the login_user method from the user_login.sh script
            String output = this.bashRunner.execute("user_login.sh", new String[]{username, password});

            // Split the output into an array of strings
            String[] parts = output.split(" ");
            String userId = parts[0];
            String role = parts[1];

            // Check the role of the user
            if (role.equalsIgnoreCase("admin")) {
                return new Admin(userId, username, password);
            } else if (role.equalsIgnoreCase("patient")) {
                return new Patient(userId, username, password);
            } else {
                return null;
            }
        } catch (Exception e) {
            // Handle exception
            System.err.println("Error executing script: " + e.getMessage());
        }
        return null;
    }

    public Patient createPatient(String email) {
        Integer usersCount = this.countUsers();
        String nextUserId = (++usersCount).toString();
        String code = UUID.randomUUID().toString();

        String[] args = { nextUserId, Role.PATIENT.toString(), email, code };
        this.bashRunner.execute("create_user.sh", args);
        return new Patient(nextUserId, email, code);
    }

    public Patient verifyPatient(String email, String code) {
        String[] args = { email, code };
        String output = this.bashRunner.execute("verify_user.sh", args);
        if (output != null){
            return new Patient(output, email, code);
        }
        return null;
    }

    public void updatePatientDetails(Patient patient) {
        String[] args = { patient.password, patient.firstName, patient.lastName, patient.dateOfBirth, patient.hivStatus.toString(), patient.dateOfDiagnosis, patient.isOnART.toString(), patient.artStartDate, patient.country, patient.id };
        this.bashRunner.execute("register_patient.sh", args);
    }

    public Integer countUsers(){
        String output = this.bashRunner.execute("count_users.sh", null);
        Integer numOfUsers = Integer.valueOf(output.trim());
        return numOfUsers;
      }

    public void logout(){
        this.bashRunner.execute("logout.sh", null);
    }
}