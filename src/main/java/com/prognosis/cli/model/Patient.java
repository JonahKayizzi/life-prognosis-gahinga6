package com.prognosis.cli.model;

// Import the User class
public class Patient extends User {
    public String dateOfBirth;
    public Boolean hivStatus;
    public String dateOfDiagnosis;
    public Boolean isOnART;
    public String artStartDate;
    public String country;

    // Constructor
    public Patient(String id, String email, String code) {
        super(id, Role.PATIENT, email, code);
    }
}