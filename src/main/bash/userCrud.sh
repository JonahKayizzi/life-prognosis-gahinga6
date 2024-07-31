#!/bin/bash

#crud operations for user management

#global salt to be used for openssl hashing
salt=$(openssl rand -base64 6)

#create user
email="jkayizzi@andrew.cmu.edu"

#register user profile
create_user(){
    password="123"
    stored_hashed_password=$(openssl passwd -1 -salt "$salt" "$password")
    echo "$email $stored_hashed_password" >> user_store.txt
}

#Login user
login_user() {
    username=$1
    password=$2
    hashed_password=$(openssl passwd -1 -salt "$salt" "$password")
    if grep -q "$hashed_password" user_store.txt; then
        echo "Successful login"
    else
        echo "Login unsuccessful"
    fi
}