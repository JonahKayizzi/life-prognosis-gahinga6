package com.prognosis.cli.view;

public class PatientView {
    public String promptEmail() {
        System.out.println("Enter your email:");
        return System.console().readLine();
    }

    public String promptCode() {
        System.out.println("Enter your code:");
        return System.console().readLine();
    }

    public String promptPassword() {
        System.out.println("Enter your email:");
        return System.console().readLine();
    }

    public String promptFirstName() {
        System.out.println("Enter your first name:");
        return System.console().readLine();
    }

    public String promptLastName() {
        System.out.println("Enter your last name:");
        return System.console().readLine();
    }

    public String promptDateOfBirth() {
        System.out.println("Enter your date of birth:");
        return System.console().readLine();
    }

    public String promptHivStatus() {
        System.out.println("Enter your HIV status:");
        return System.console().readLine();
    }

    public String promptDateOfDiagnosis() {
        System.out.println("Enter your date of diagnosis:");
        return System.console().readLine();
    }


    public String promptIsOnART() {
        System.out.println("Are you taking anti retro viral drugs:");
        return System.console().readLine();
    }

    public String promptArtStartDate() {
        System.out.println("Enter the date you started taking  anti retro viral drugs:");
        return System.console().readLine();
    }

    public String promptCountry() {
        System.out.println("Enter your country of residence:");
        return System.console().readLine();
    }
}
