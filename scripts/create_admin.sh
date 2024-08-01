#!/bin/bash

#crud operations for user management

#global salt to be used for openssl hashing
salt=$(openssl rand -base64 6)
code=$1

#set static admin password
password="admin"
#hash the asdmin password
stored_hashed_password=$(openssl passwd -1 -salt "$salt" "$password")
#store admin crendentials in the data store
echo "1 admin admin $code $stored_hashed_password" > "$PWD/user-store.txt"
