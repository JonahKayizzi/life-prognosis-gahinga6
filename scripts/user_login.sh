#!/bin/bash

#pick email and password from user supplied arguments
email=$1
password=$2
salt="salt"
#hash password for before checking user records
hashed_password=$(openssl passwd -1 -salt "$salt" "$password")
escaped_password=$(echo "$hashed_password" | sed 's/\$/\\$/g')
# return the record matching the supplied email and password
user_record=$(grep "$email.*$escaped_password" "$PWD/user-store.txt")
#check if record exits
if [ -n "$user_record" ]; then
    #check for the user type
    user_id=$(echo "$user_record" | awk '{print $1}')
    user_type=$(echo "$user_record" | awk '{print $2}')
    user_email=$(echo "$user_record" | awk '{print $3}')
    user_code=$(echo "$user_record" | awk '{print $4}')
    user_password=$(echo "$user_record" | awk '{print $5}')
    user_first_name=$(echo "$user_record" | awk '{print $6}')
    user_last_name=$(echo "$user_record" | awk '{print $7}')
    user_dob=$(echo "$user_record" | awk '{print $8}')
    user_hiv_status=$(echo "$user_record" | awk '{print $9}')
    user_country=$(echo "$user_record" | awk '{print $10}')
    user_diagnosis_date=$(echo "$user_record" | awk '{print $11}')
    user_is_on_art=$(echo "$user_record" | awk '{print $12}')
    user_art_start_date=$(echo "$user_record" | awk '{print $13}')

    user_data="$user_id $user_type $user_email $user_code $user_password $user_first_name $user_last_name $user_dob $user_hiv_status $user_country $user_diagnosis_date $user_is_on_art $user_art_start_date"
    echo "$user_data" > "$PWD/session-storage.txt"
    echo "$user_data"
else
    echo "error"
fi