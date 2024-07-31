#!/bin/bash

#crud operations for user management

#global salt to be used for openssl hashing
salt=$(openssl rand -base64 6)

create_admin(){
    #set static admin password
    password="admin"
    #hash the asdmin password
    stored_hashed_password=$(openssl passwd -1 -salt "$salt" "$password")
    #store admin crendentials in the data store
    echo "1 admin admin $stored_hashed_password" > user_store.txt
}

#register user profile
create_user(){
    #pick email and password from user supplied arguments
    email=$1
    password=$2
    #hash password for storing
    stored_hashed_password=$(openssl passwd -1 -salt "$salt" "$password")
    # check number of existing users
    userid=$(sed -n '$=' user_store.txt)
    # increment by 1 to obtain new userid
    ((userid++))
    # store new user credentials in the data store
    echo "$userid patient $email $stored_hashed_password" >> user_store.txt
}

#Login user
login_user() {
    #pick email and password from user supplied arguments
    email=$1
    password=$2
    #hash password for before checking user records
    hashed_password=$(openssl passwd -1 -salt "$salt" "$password")
    # return the record matching the supplied email and password
    user_record=$(grep "$email.*$hashed_password" user_store.txt)
    #check if record exits
    if [ -n "$user_record" ]; then
        #check for the user type
        user_type=$(echo "$user_record" | awk '{print $2}')
        # if user type is admin
        if [ "$user_type" == "admin" ]; then
            echo "admin"
        #if it is a patient
        else
            user_id=$(echo "$user_record" | awk '{print $1}')
            echo "$user_id"
        fi
    #if record does not exist
    else
        echo "Login unsuccessful"
    fi
}