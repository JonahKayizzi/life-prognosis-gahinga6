#!/bin/bash
echo "Type, Email, First Name, Last Name, DoB, HIV Status, Country, Diagnosis Date, On ART, ART Start Date" > "$PWD/user-store.csv"

while IFS= read -r line
do
    # Code to execute if the condition is true
    user_id=$(echo "$line" | awk '{print $1}')
    user_type=$(echo "$line" | awk '{print $2}')
    user_email=$(echo "$line" | awk '{print $3}')
    user_first_name=$(echo "$line" | awk '{print $6}')
    user_last_name=$(echo "$line" | awk '{print $7}')
    user_dob=$(echo "$line" | awk '{print $8}')
    user_hiv_status=$(echo "$line" | awk '{print $9}')
    user_country=$(echo "$line" | awk '{print $10}')
    user_diagnosis_date=$(echo "$line" | awk '{print $11}')
    user_is_on_art=$(echo "$line" | awk '{print $12}')
    user_art_start_date=$(echo "$line" | awk '{print $13}')
    if [ "$user_type" = "PATIENT" ]
    then
        echo "$user_type,$user_email,$user_first_name,$user_last_name,$user_dob,$user_hiv_status,$user_country,$user_diagnosis_date,$user_is_on_art,$user_art_start_date" >> "$PWD/user-store.csv"
    fi
done < "$PWD/user-store.txt"