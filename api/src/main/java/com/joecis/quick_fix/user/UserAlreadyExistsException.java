package com.joecis.quick_fix.user;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String username){
        super("The username " + username + " has alrady been taken.");
    }
}
