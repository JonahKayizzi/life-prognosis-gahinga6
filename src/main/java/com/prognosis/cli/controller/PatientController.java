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

        String id =  patientService.validateCode(email, code);


        if(id.equals("0")){
            System.out.println("Wrong Code");
            return;
        }
        

        String firstName = patientView.promptFirstName();
        String lastName = patientView.promptLastName();
        String dateOfBirth = patientView.promptDateOfBirth();
        String password = patientView.promptPassword();
        String hivStatus = patientView.promptHivStatus();
        String isOnART = patientView.promptIsOnART();
        String artStartDate = patientView.promptArtStartDate();
        String dateOfDiagnosis = patientView.promptDateOfDiagnosis();
        String country = patientView.promptCountry();

        Patient patient = new Patient(id, Role.PATIENT, email, code, password, firstName, lastName, dateOfBirth, hivStatus, dateOfDiagnosis, isOnART, artStartDate, country);

        patientService.registerUser(patient);
        System.exit(0);
    }
}
