#!/bin/bash

#pick email and code from user supplied arguments
email=$1
code=$2

user_store="$PWD/user-store.txt"
# return the record matching the supplied email and password
user_record=$(grep "$email.*$code" $user_store)
#check if record exits
if [ -n "$user_record" ]; then 
    user_id=$(echo "$user_record" | awk '{print $1}')
    #return the user id
    echo "$user_id"
#if record does not exist return 0
else
    echo 0
fi