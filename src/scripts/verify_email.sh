#!/bin/bash

#pick email from user supplied arguments
email=$1

user_store="$PWD/user-store.txt"
# return the record matching the supplied email
user_record=$(grep "$email" $user_store)
#check if record exits
if [ -n "$user_record" ]; then 
    user_id=$(echo "$user_record" | awk '{print $1}')
    #return 1f email exists
    echo 1
#if record does not exist return 0
else
    echo 0
fi