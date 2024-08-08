logged_in_user=$(head "$PWD/session-storage.txt")
email=$(echo "$logged_in_user" | awk -F' ' '{print $3}')
user_record=$(grep "$email" "$PWD/user-store.txt")

user_id=$(echo "$user_record" | awk '{print $1}')
user_type=$(echo "$user_record" | awk '{print $2}')
user_email=$(echo "$user_record" | awk '{print $3}')
user_code=$(echo "$user_record" | awk '{print $4}')
user_first_name=$(echo "$user_record" | awk '{print $6}')
user_last_name=$(echo "$user_record" | awk '{print $7}')
user_dob=$(echo "$user_record" | awk '{print $8}')
user_hiv_status=$(echo "$user_record" | awk '{print $9}')
user_country=$(echo "$user_record" | awk '{print $10}')
user_diagnosis_date=$(echo "$user_record" | awk '{print $11}')
user_is_on_art=$(echo "$user_record" | awk '{print $12}')
user_art_start_date=$(echo "$user_record" | awk '{print $13}')

echo "$user_id $user_type $user_email $user_code $user_first_name $user_last_name $user_dob $user_hiv_status $user_country $user_diagnosis_date $user_is_on_art $user_art_start_date"
