logged_in_user=$(head "$PWD/session-storage.txt")
email=$(echo "$logged_in_user" | awk -F' ' '{print $3}')
user_record=$(grep "$email" "$PWD/user-store.txt")

user_id=$(echo "$user_record" | awk '{print $1}')
user_type=$(echo "$user_record" | awk '{print $2}')
user_email=$(echo "$user_record" | awk '{print $3}')
user_code=$(echo "$user_record" | awk '{print $4}')

echo "$user_id $user_type $user_email $user_code"
