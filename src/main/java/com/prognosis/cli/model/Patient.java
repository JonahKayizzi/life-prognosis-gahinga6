package com.prognosis.cli.model;

import java.util.Date;

public class Patient extends User {
    public String firstName;
    public String lastName;
    public Date dateOfBirth;
    public Boolean hivStatus;
    
    public Date dateOfDiagnosis;
    public Boolean isOnART;
    public Date artStartDate;
    public String country;

    public Patient(int id, Role role, String email, String code, String password, String firstName, String lastName, Date dateOfBirth, Boolean hivStatus, Date dateOfDiagnosis ,Boolean isOnART, Date artStartDate, String country) {
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