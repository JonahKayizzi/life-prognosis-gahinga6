#register user profile
create_user(){
    #pick email and password from user supplied arguments
    email=$1
    password=$2
    #hash password for storing
    stored_hashed_password=$(openssl passwd -1 -salt "$salt" "$password")
    # check number of existing users
    userid=$(sed -n '$=' user_store.txt)
    # increment by 1 to obtain new userid
    ((userid++))
    # store new user credentials in the data store
    echo "$userid patient $email $stored_hashed_password" >> user_store.txt
}