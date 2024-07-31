#!/bin/bash

#crud operations for user management

#Login user
login_user() {
    username=$1
    password=$2
    salt=$(openssl rand -base64 6)
    hashed_password=$(openssl passwd -1 "$salt" "$password")
    echo "Username: $username, Hashed Password: $hashed_password"
}

login_user jkayizzi 123