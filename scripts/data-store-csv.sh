#!/bin/bash

patient_info_file="$PWD/user-store.txt"
output_file="user-store.csv"

awk 'BEGIN { printf "column1, column2, column3, column4, column5, column6, column7, column8, column9, column10, column11, column12\n" } { printf "%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s\n", $2, $3, $4, $6, $7, $8, $9, $10, $11, $12, $13, $14 }' "$patient_info_file" > "$output_file"

if [[ $? -eq 0 ]]; then
    echo "CSV file created: $output_file"
else
    echo "An error occurred while creating the CSV file"
fi