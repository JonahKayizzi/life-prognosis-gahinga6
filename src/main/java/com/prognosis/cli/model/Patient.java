package com.prognosis.cli.model;

// Import the User class
public class Patient extends User {
    public String dateOfBirth;
    public HIVStatus hivStatus;
    public String dateOfDiagnosis;
    public String isOnART;
    public String artStartDate;
    public String country;

    public enum HIVStatus {
        POSITIVE, NEGATIVE
    }

    // Constructor
    public Patient(String id, String email, String code) {
        super(id, Role.PATIENT, email, code);
    }
}