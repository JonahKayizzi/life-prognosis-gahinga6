package model;
public class Admin extends User {   
    
    // Constructor
    public Admin(String id, String email, String code) {
        super(id, Role.ADMIN, email, code);
    }
}