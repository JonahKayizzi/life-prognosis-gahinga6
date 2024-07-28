package com.prognosis.cli.model;

import java.util.Date;

public class Patient extends User {
    private Date dateOfBirth;
    private Boolean hivStatus;
    private Date diagnosisDate;
    private Boolean onART;
    private Date artStartDate;
    private String country;

    public Patient() {
        super();
    }

    private void GetProfile() {
        // Get profile
    }

    private void UpdateProfile() {
        // Update profile
    }

    private void downCalendar() {
        
    }
}