package com.prognosis.cli.service;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.time.Period;

import com.prognosis.cli.model.Patient;
import com.prognosis.cli.utils.BashRunner;

public class PatientService {
    private final BashRunner bashRunner = new BashRunner();

    public String validateCode(String email, String code) {
        String[] args = { email, code };
        String id = this.bashRunner.execute("verify_user.sh", args);

        return id;
    }


    public void registerUser(Patient patient) {
        String[] args = { patient.password, patient.firstName, patient.lastName, patient.dateOfBirth, patient.hivStatus,patient.dateOfDiagnosis, patient.isOnART, patient.artStartDate, patient.country, patient.id};
        this.bashRunner.execute("register_patient.sh", args);
    }

    private Float getLifeExpectancy(String country){
        String[] args = { country };
        String expectedLifeExpectancyInString =  this.bashRunner.execute("get-country-life-expectancy.sh", args);
        Float expectedLifeExpectancy =  Float.parseFloat(expectedLifeExpectancyInString);
        return expectedLifeExpectancy;
    }

    private Float calculateTimeSince(String time){
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate parsedDate = LocalDate.parse(time, formatter);
        LocalDate now = LocalDate.now();

        return (float) Period.between(parsedDate, now).getYears();
    }

    private Integer calculateTimeBetween(String startTime, String endTime){
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate parsedStartDate = LocalDate.parse(startTime, formatter);
        LocalDate parsedEndDate = LocalDate.parse(endTime, formatter);

        return Period.between(parsedStartDate, parsedEndDate).getYears();
    }

    public Float calculateLifeSpan(Patient patient) {
        Float expectedLifeExpectancy = this.getLifeExpectancy(patient.country);
        Float remainingTime =  expectedLifeExpectancy - this.calculateTimeSince(patient.dateOfBirth);
        
        if(patient.hivStatus == "false") {
           return remainingTime;
        }

        if(patient.isOnART == "false"){
            Float calculateTimeAfterDiagnosis = this.calculateTimeSince(patient.dateOfDiagnosis);
            return Math.min(remainingTime, 5 - calculateTimeAfterDiagnosis) ;
        }

        Integer timeSince = this.calculateTimeBetween(patient.dateOfDiagnosis, patient.artStartDate);

        return (float)(remainingTime * Math.pow(0.9, 1+timeSince));
    }
}
