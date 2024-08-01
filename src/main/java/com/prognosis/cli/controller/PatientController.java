package com.prognosis.cli.controller;

import java.util.Date;

import com.prognosis.cli.model.Patient;
import com.prognosis.cli.model.User.Role;
import com.prognosis.cli.service.PatientService;
import com.prognosis.cli.view.PatientView;

public class PatientController {
    PatientService patientService = new PatientService();
    PatientView patientView = new PatientView();


    public void registerUser() {
        String email = patientView.promptEmail();
        String code = patientView.promptCode();

       Integer id =  patientService.validateCode(email, code);


        String firstName = patientView.promptFirstName();
        String lastName = patientView.promptLastName();
        Date dateOfBirth = patientView.promptDateOfBirth();
        String password = patientView.promptPassword();
        Boolean hivStatus = patientView.promptHivStatus();
        Boolean isOnART = patientView.promptIsOnART();
        Date artStartDate = patientView.promptArtStartDate();
        Date dateOfDiagnosis = patientView.promptDateOfDiagnosis();
        String country = patientView.promptCountry();

        Patient patient = new Patient(id, Role.PATIENT, email, code, password, firstName, lastName, dateOfBirth, hivStatus, dateOfDiagnosis, isOnART, artStartDate, country);

        patientService.registerUser(patient);
    }
}
