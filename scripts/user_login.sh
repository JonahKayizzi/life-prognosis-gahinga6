#!/bin/bash

#pick email and password from user supplied arguments
email=$1
password=$2
#hash password for before checking user records
hashed_password=$(openssl passwd -1 -salt "$salt" "$password")
# return the record matching the supplied email and password
user_record=$(grep "$email.*$hashed_password" "$PWD/user-store.txt")
#check if record exits
if [ -n "$user_record" ]; then
    #check for the user type
    user_type=$(echo "$user_record" | awk '{print $2}')
    # if user type is admin
    if [ "$user_type" == "admin" ]; then
        #return admin
        echo "admin"
    #if it is a patient
    else
        user_id=$(echo "$user_record" | awk '{print $1}')
        #return the user id
        echo "$user_id"
    fi
#if record does not exist
else
    echo "Login unsuccessful"
fi