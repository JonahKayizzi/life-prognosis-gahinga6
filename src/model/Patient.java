package model;

public class Patient extends User {
    public String dateOfBirth;
    public HIVStatus hivStatus;
    public String dateOfDiagnosis;
    public String isOnART;
    public String artStartDate;
    public String country;
    public String dateOfDeath;

    public enum HIVStatus {
        POSITIVE, NEGATIVE
    }

    public Patient(String id, String email, String code) {
        super(id, Role.PATIENT, email, code);
    }

    public Patient(String id, String email, String code, String password, String firstName, String lastName, String dateOfBirth, String hivStatus, String dateOfDiagnosis, String isOnART, String artStartDate, String country) {
        super(id, Role.PATIENT, email, code);

        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.hivStatus = hivStatus == "yes" ? HIVStatus.POSITIVE : HIVStatus.NEGATIVE;
        this.dateOfDiagnosis = dateOfDiagnosis;
        this.isOnART = isOnART;
        this.artStartDate = artStartDate;
        this.country = country;
    }
}