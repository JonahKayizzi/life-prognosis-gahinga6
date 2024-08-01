package com.prognosis.cli.service;
import com.prognosis.cli.model.Patient;
import com.prognosis.cli.utils.BashRunner;

public class PatientService {
    private final BashRunner bashRunner = new BashRunner();

    public String validateCode(String email, String code) {
        String[] args = { email, code };
        String id = this.bashRunner.execute("verify_user.sh", args);

        if(id == "0"){
            throw new Error("Wrong code");
        }

        return id;
    }


    public void registerUser(Patient patient) {
        String[] args = { patient.password, patient.firstName, patient.lastName, patient.dateOfBirth, patient.hivStatus,patient.dateOfDiagnosis, patient.isOnART, patient.artStartDate, patient.country, patient.id};
        this.bashRunner.execute("register_patient.sh", args);
    }
}
