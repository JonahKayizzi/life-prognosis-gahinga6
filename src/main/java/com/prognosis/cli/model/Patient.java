package com.prognosis.cli.model;

public class Patient extends User {
    public String firstName;
    public String lastName;
    public String dateOfBirth;
    public String hivStatus;
    
    public String dateOfDiagnosis;
    public String isOnART;
    public String artStartDate;
    public String country;

    public Patient(String id, Role role, String email, String code, String password, String firstName, String lastName, String dateOfBirth, String hivStatus, String dateOfDiagnosis ,String isOnART, String artStartDate, String country) {
        super(id, Role.PATIENT, email, code, password);

        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.hivStatus = hivStatus;
        this.dateOfDiagnosis = dateOfDiagnosis;
        this.isOnART = isOnART;
        this.artStartDate = artStartDate;
        this.country = country;
    }
}