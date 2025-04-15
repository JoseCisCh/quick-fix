package com.joecis.quick_fix.user;

class UserNotFoundException extends RuntimeException {
    
    UserNotFoundException(Long id) {
        super("No user found for id " + id);
    }
}
