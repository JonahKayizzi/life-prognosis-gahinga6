package service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import model.Admin;
import model.Patient;
import model.Patient.HIVStatus;
import model.User;
import model.User.Role;
import utils.BashRunner;

public class UserService {

    // Create an instance of the BashRunner class
    private final BashRunner bashRunner = new BashRunner();
    // Implement the loginUser method

    private User initUser(String output) {
        String[] parts = output.split(" ");
        String userId = parts[0];
        String role = parts[1];
        String email = parts[2];
        String code = parts[3];

        if (role.equalsIgnoreCase("admin")) {
            return new Admin(userId, email, code);
        }

        String password = parts[4];
        String firstName = parts[5];
        String lastName = parts[6];
        String dateOfBirth = parts[7];
        String hivStatus = parts[8];
        String country = parts[9];
        String dateOfDiagnosis = parts[10];
        String isOnArt = parts[11];
        String artStartDate = parts[12];

        // Check the role of the user
        if (role.equalsIgnoreCase("patient")) {
            return new Patient(userId, email, code, password, firstName, lastName, dateOfBirth, hivStatus, dateOfDiagnosis, isOnArt, artStartDate, country);
        } else {
            return null;
        }
    }

    public User loginUser(String username, String password) {
        try {
            // Execute the login_user method from the user_login.sh script
            String output = this.bashRunner.execute("user_login.sh", new String[]{username, password});
            if (output.equalsIgnoreCase("error")) {
                return null;
            } else {
                return this.initUser(output);
            }
        } catch (Exception e) {
            // Handle exception
            System.err.println("Error executing script: " + e.getMessage());
        }
        return null;
    }

    // Implement the createPatient method
    public Patient createPatient(String email) {
        // Get the number of users in the store
        Integer usersCount = this.countUsers();
        // Generate the next user ID by incrementing number of users
        String nextUserId = (++usersCount).toString();
        // Generate a random code for the user
        String code = UUID.randomUUID().toString();

        // Generate an array of arguments for the create_user.sh script
        String[] args = {nextUserId, Role.PATIENT.toString(), email, code};
        // Execute the create_user.sh script
        this.bashRunner.execute("create_user.sh", args);
        // Return a new Patient object
        return new Patient(nextUserId, email, code);
    }

    // Implement the verifyPatient method that verifies the patient and code given by the admin
    public Patient verifyPatient(String email, String code) {
        // Generate an array of arguments for the verify_user.sh script
        String[] args = {email, code};
        // Execute the verify_user.sh script
        String output = this.bashRunner.execute("verify_user.sh", args);
        // Check if the patient exists
        if (!output.equalsIgnoreCase("0")) {
            // Return a new Patient object
            return new Patient(output, email, code);
        }
        // if patient does not exist, return null
        return null;
    }

    // Implement the updatePatientDetails method
    public void updatePatientDetails(Patient patient) {
        // Generate an array of arguments for the update_patient.sh script
        String[] args = {patient.password, patient.firstName, patient.lastName, patient.dateOfBirth, patient.hivStatus.toString(), patient.dateOfDiagnosis, patient.isOnART, patient.artStartDate, patient.country, patient.id, patient.email, patient.code};
        // Execute the update_patient.sh script
        this.bashRunner.execute("register_patient.sh", args);
    }

    // Implement the countUsers method
    public Integer countUsers() {
        // Execute the count_users.sh script to get the number of users in the user store
        String output = this.bashRunner.execute("count_users.sh", null);
        // convert result to integer
        Integer numOfUsers = Integer.valueOf(output.trim());
        // return the number of users
        return numOfUsers;
    }

    // Implement the logout method
    public void logout() {
        // Execute the logout.sh script to empty the session storage
        this.bashRunner.execute("logout.sh", null);
    }

    public User getProfile() {
        String output = this.bashRunner.execute("get_profile.sh", null);
        User user = this.initUser(output);
        if (user instanceof Patient patient) {
            Long remainingLifeSpan = this.calculateLifeSpan(patient);
            String dateOfDeath = this.getExpectedDateOfDeath(remainingLifeSpan);

            patient.dateOfDeath = dateOfDeath;

            return patient;
        }

        return user;
    }

    private Float getLifeExpectancy(String country) {
        String[] args = {country};
        String expectedLifeExpectancyInString = this.bashRunner.execute("get-country-life-expectancy.sh", args);
        Float expectedLifeExpectancy = Float.parseFloat(expectedLifeExpectancyInString);
        return expectedLifeExpectancy;
    }

    private Float calculateTimeSince(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate parsedDate = LocalDate.parse(time.trim(), formatter);
        LocalDate now = LocalDate.now();

        return (float) Period.between(parsedDate, now).getYears();
    }

    public String getExpectedDateOfDeath(Long remainingYears) {
        LocalDate now = LocalDate.now();
        LocalDate dateOfDeath = now.plusYears(remainingYears);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return dateOfDeath.format(formatter);
    }

    private Integer calculateTimeBetween(String startTime, String endTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate parsedStartDate = LocalDate.parse(startTime.trim(), formatter);
        LocalDate parsedEndDate = LocalDate.parse(endTime.trim(), formatter);

        return Period.between(parsedStartDate, parsedEndDate).getYears();
    }

    public long calculateLifeSpan(Patient patient) {
        Float expectedLifeExpectancy = this.getLifeExpectancy(patient.country);
        Float remainingTime = expectedLifeExpectancy - this.calculateTimeSince(patient.dateOfBirth);

        if (patient.hivStatus == HIVStatus.NEGATIVE) {
            return Math.round(remainingTime);
        }

        if (patient.isOnART == "false") {
            Float calculateTimeAfterDiagnosis = this.calculateTimeSince(patient.dateOfDiagnosis);
            return Math.round(Math.min(remainingTime, 5 - calculateTimeAfterDiagnosis));
        }

        Integer timeSince = this.calculateTimeBetween(patient.dateOfDiagnosis, patient.artStartDate);

        return Math.round((remainingTime * Math.pow(0.9, 1 + timeSince)));
    }

    public Boolean checkIfCountryCodeIsValid(String countryCode) {
        String[] args = {countryCode};
        String output = this.bashRunner.execute("check_if_country_exists.sh", args);

        return output.trim().equals("1");
    }

    public void exportDataToCSV() {
        try {
            // Execute the exportDataToCSV method from the user_login.sh script
            this.bashRunner.execute("data-store-csv.sh", null);
        } catch (Exception e) {
            // Handle exception
            System.err.println("Error executing script: " + e.getMessage());
        }
    }

    public void exportAnalytics() {
        try {
            // Execute the exportDataToCSV method from the user_login.sh script
            String patientList = this.bashRunner.execute("get_all_users.sh", null);

            List<Long> survivalRatesList
                    = new ArrayList<>();

            for (String p : patientList.split("\n")) {
                Patient patient = (Patient) this.initUser(p);
                Long lifespan = this.calculateLifeSpan(patient);
                survivalRatesList.add(lifespan);
            }

            Float[] survivalRates = new Float[survivalRatesList.size()];
            for (int i = 0; i < survivalRatesList.size(); i++) {
                survivalRates[i] = survivalRatesList.get(i).floatValue();
            }

            int average = this.calculateAverage(survivalRates);
            Long percentile10Th = this.calculatePercentile(survivalRates, 15);
            Long percentile25Th = this.calculatePercentile(survivalRates, 25);
            Long percentile50Th = this.calculatePercentile(survivalRates, 50);
            Long percentile75Th = this.calculatePercentile(survivalRates, 75);
            Long percentile90Th = this.calculatePercentile(survivalRates, 90);

            FileWriter fileWriter = new FileWriter(String.format("%s/statistics.csv", System.getProperty("user.dir")));
            BufferedWriter writer = new BufferedWriter(fileWriter);

            String title = "Expected Survival Rate Statistics \n";
            String header = "Average, 10th Percentile, 25th Percentile, 50th Percentile (Median), 75th Percentile, 90th Percentile \n";
            String content = String.format("%s,%s,%s,%s,%s,%s", average, percentile10Th, percentile25Th, percentile50Th, percentile75Th, percentile90Th);

            writer.write(title + header + content);
            writer.close();

        } catch (Exception e) {
            // Handle exception
            //System.err.println("Error executing script: " + e.getMessage());
        }
    }

    private int calculateAverage(Float[] survivalRates) {
        int sum = 0;
        for (Float survivalRate : survivalRates) {
            sum += survivalRate;
        }

        return Math.round(sum / survivalRates.length);
    }

    public Long calculatePercentile(Float[] survivalRates, int percentile) {
        Arrays.sort(survivalRates);
        Double index = (percentile / 100.0) * (survivalRates.length - 1);
        int lowerIndex = (int) Math.floor(index);
        int upperIndex = (int) Math.ceil(index);
        if (lowerIndex == upperIndex) {
            return (long) Math.round(survivalRates[lowerIndex]);
        } else {
            return Math.round(survivalRates[lowerIndex] + (index - lowerIndex) * (survivalRates[upperIndex] - survivalRates[lowerIndex]));
        }
    }

    public Boolean checkIfEmailExists(String email) {
        String[] args = {email};
        String output = this.bashRunner.execute("verify_email.sh", args);
        return output.trim().equals("1");
    }

    public void exportDataToCalendar(Patient patient) {
        SimpleDateFormat outputFormatter = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat inputFormatter = new SimpleDateFormat("MM/dd/yyyy");
        try {
            // Execute the exportDataToCalendar method from the user_login.sh script
            Long lifeSpan = this.calculateLifeSpan(patient);
            String dateOfDeath = this.getExpectedDateOfDeath(lifeSpan);
            System.out.println(dateOfDeath);
            Date date = inputFormatter.parse(dateOfDeath);
            String calendarDate = outputFormatter.format(date);
            String[] args = {String.format("%s", calendarDate), patient.email};

            this.bashRunner.execute("createIcalendar.sh", args);
        } catch (Exception e) {
            // Handle exception
            System.err.println("Error executing script: " + e.getMessage());
        }
    }
}
