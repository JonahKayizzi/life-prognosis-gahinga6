#!/bin/bash

# Usage: ./fetch-patient-profile.sh <patient_id>

# Check if an argument (patient ID) is provided
if [ $# -ne 1 ]; then
    echo "Enter the filename followed bu patient ID: $0 <patient_id>"
    exit 1
fi

# patient ID to search for
patient_id="$1"

# Path to the patient info file
patient_info_file="~/user-store.txt"

# Search for the patient ID in the file
patient_info=$(grep "^$patient_id " "$patient_info_file")

# Check if the patient was found
if [ -z "$patient_info" ]; then
    echo "patient ID '$patient_id' not found."
    exit 2
fi

# Extract other information
patientname=$(echo "$patient_info" | cut -d ' ' -f 2)
email=$(echo "$patient_info" | cut -d ' ' -f 3)
other_info=$(echo "$patient_info" | cut -d ' ' -f 4-)

# Print the results
echo "patient ID: $patient_id"
echo "patientname: $patientname"
echo "Email: $email"
echo "Other Info: $other_info"

exit 0
