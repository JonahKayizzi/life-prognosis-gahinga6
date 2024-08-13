#!/bin/bash

seed_size=500

create_admin(){
    salt="salt"
    code=$(uuidgen)

    #set static admin password
    password="admin"
    #hash the asdmin password
    stored_hashed_password=$(openssl passwd -1 -salt "$salt" "$password")
    #store admin crendentials in the data store
    echo "1 ADMIN admin@life-prognosis.com $code $stored_hashed_password" > "$PWD/user-store.txt"
}

random_date() {
    start_date=$1
    end_date=$2

    random_year=$(shuf -i $start_date-$end_date -n 1)
    random_month=$(shuf -i 10-12 -n 1)
    random_day=$(shuf -i 10-28 -n 1)  # to avoid invalid dates
    echo "$random_month/$random_day/$random_year"
}

seed_users(){
for ((i=1; i<=$seed_size; i++))
do
    echo "seeding [$i/$seed_size] users ..."
    id=$((i+1))
    first_name="$(openssl rand -base64 7 | tr -dc 'a-z')"
    last_name="$(openssl rand -base64 7 | tr -dc 'a-z')"
    email="$first_name-$last_name@life-prognosis.com"
    code=$(uuidgen)
    password=$(openssl passwd -1 -salt "$salt" "password")
    dob="$(random_date 1970 2005)"
    country=$(tail -n +2 "$PWD/life-expectancy.csv" |shuf -n 1 | awk -F ',' '{print $(NF-1)}')

    hiv_status=$(shuf -n 1 -e "POSITIVE" "NEGATIVE")

    if [ "$hiv_status" == "POSITIVE" ];
    then
        date_of_diagnosis="$(random_date 2005 2020)"
        is_on_art=$(shuf -n 1 -e "yes" "no")

        if [ "$is_on_art" == "yes" ];
        then
            art_start_date="$(random_date 1920 2024)"
        else 
            art_start_date=null
        fi
    else
        date_of_diagnosis=null
        is_on_art=no
        art_start_date=null
    fi  

    echo "$id PATIENT $email $code $password $first_name $last_name $dob $hiv_status $country $date_of_diagnosis $is_on_art $art_start_date" >> "$PWD/user-store.txt"
done
}


create_admin
seed_users

echo "Done seeding users"
