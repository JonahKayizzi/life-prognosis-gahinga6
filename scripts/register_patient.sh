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
country=$8
user_id=$9

#hash password for storing
stored_hashed_password=$(openssl passwd -1 -salt "$salt" "$password")
# store new user credentials in the data store

#file path to store user data
user_store="../user_store.txt"

#update like with given user id
awk -v user_id="$user_id" -v first_name="$first_name" -v last_name="$last_name" -v dob="$dob" -v hiv_status="$hiv_status" -v country="$country" -v stored_hashed_password="$stored_hashed_password" -v user_type="patient" -v email="$email" -v diagnosis_date="$diagnosis_date" -v on_art="$on_art" '
BEGIN { OFS=" " } 
{
    if ($1 == user_id) {
        $2 = stored_hashed_password
        $3 = user_type
        $4 = first_name
        $5 = last_name 
        $6 = dob
        $7 = hiv_status
        $8 = country
        $9 = diagnosis_date
        $10 = on_art
    } 
    print 
}' "$user_store" > temp && mv temp "$user_store"

