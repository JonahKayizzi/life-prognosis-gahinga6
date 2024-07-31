#!/bin/bash

#crud operations for user management

#global salt to be used for openssl hashing
salt=$(openssl rand -base64 6)

#create user
email="jkayizzi@andrew.cmu.edu"

#register user profile
password="123"
stored_hashed_password=$(openssl passwd -1 -salt "$salt" "$password")
echo "Stored hashed password is:  $stored_hashed_password"

#Login user
login_user() {
    username=$1
    password=$2
    salt=$(openssl rand -base64 6)
    hashed_password=$(openssl passwd -1 "$salt" "$password")
    echo "Username: $username, Hashed Password: $hashed_password"
}

login_user jkayizzi 123