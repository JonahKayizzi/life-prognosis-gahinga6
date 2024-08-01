package com.prognosis.cli.service;
import com.prognosis.cli.model.Patient;
import com.prognosis.cli.utils.BashRunner;

public class PatientService {
    private final BashRunner bashRunner = new BashRunner();

    public Integer validateCode(String email, String code) {
        String[] args = { email, code };
        // this.bashRunner.execute("create_user.sh", args);

        return 3;
    }


    public void registerUser(Patient patient) {
        String[] args = { patient.firstName, patient.lastName, patient.email, patient.code, patient.password };
        // this.bashRunner.execute("", args);

    }




}
