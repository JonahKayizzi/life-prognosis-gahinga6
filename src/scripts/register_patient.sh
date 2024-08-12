#!/bin/bash

#register patient profile

#pick patient attributes from user supplied arguments
password=$1
first_name=$2
last_name=$3
dob=$4
hiv_status=$5
diagnosis_date=$6
on_art=$7
art_start_date=$8
country=$9
user_id=${10}
user_email=${11}
user_code=${12}

#hash password for storing
stored_hashed_password=$(openssl passwd -1 -salt "salt" "$password")
# store new user credentials in the data store

#file path to store user data

user_store="$PWD/user-store.txt"


#update like with given user id
awk -v user_id="$user_id" -v first_name="$first_name" -v last_name="$last_name" -v dob="$dob" -v hiv_status="$hiv_status" -v country="$country" -v stored_hashed_password="$stored_hashed_password" -v user_type="$user_type" -v email="$email" -v diagnosis_date="$diagnosis_date" -v on_art="$on_art" -v art_start_date="$art_start_date" '
BEGIN { FS=OFS=" " } 
{
    if ($1 == user_id) {
        $5 = stored_hashed_password
        $6 = first_name
        $7 = last_name 
        $8 = dob
        $9 = hiv_status
        $10 = country
        $11 = diagnosis_date
        $12 = on_art
        $13 = art_start_date
    }

    print 
}' "$user_store" > temp && mv temp $user_store

echo "$user_id "PATIENT" $user_email $user_code $store_hashed_password $user_first_name $user_last_name $user_dob $user_hiv_status $user_country $user_diagnosis_date $user_is_on_art $user_art_start_date" > "$PWD/session-storage.txt"


