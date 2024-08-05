package com.prognosis.cli.model;

public class Patient extends User {
    public String dateOfBirth;
    public String hivStatus;
    public String dateOfDiagnosis;
    public String isOnART;
    public String artStartDate;
    public String country;

    public Patient(String id, String email, String code, String password) {
        super(id, Role.PATIENT, email, code, password);
    }
}