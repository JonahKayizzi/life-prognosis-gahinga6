#!/bin/bash

#pick email and password from user supplied arguments
email=$1
password=$2
#hash password for before checking user records
hashed_password=$(openssl passwd -1 -salt "salt" "$password")
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
    echo "$user_id $user_type $user_email" > "$PWD/session-storage.txt"
    echo "$user_id $user_type $user_email $user_code"
else
    echo "error"
fi
