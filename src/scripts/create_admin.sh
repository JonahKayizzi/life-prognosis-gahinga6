#!/bin/bash

#crud operations for user management

#global salt to be used for openssl hashing
salt="salt"
code=$(uuidgen)

#set static admin password
password="admin"
#hash the asdmin password
stored_hashed_password=$(openssl passwd -1 -salt "$salt" "$password")
#store admin crendentials in the data store
echo "1 ADMIN admin@life-prognosis.com $code $stored_hashed_password" > "$PWD/user-store.txt"
