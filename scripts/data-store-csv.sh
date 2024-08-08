#!/bin/bash

patient_info_file="$PWD/user-store.txt"
output_file="user-store.csv"

awk 'BEGIN { printf "Type, Email, Code, First Name, Last Name, DoB, HIV Status, Country, Diagnosis Date, On ART, ART Start Date\n" } row > 1 { printf "%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s\n", $2, $3, $4, $6, $7, $8, $9, $10, $11, $12, $13, $14 }' "$patient_info_file" > "$output_file"

if [[ $? -eq 0 ]]; then
    echo "CSV file created: $output_file"
else
    echo "An error occurred while creating the CSV file"
fi