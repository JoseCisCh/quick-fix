package com.joecis.quick_fix.user;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public User getById(Long id) {
        Optional<User> user = userRepository.findById(id);    
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException(id);
        }
    }

    @Transactional
    public User getByIdWithRatingsAndCases(Long id) {
        Optional<User> optUser = this.userRepository.findById(id);

        if(optUser.isEmpty()) {
            return null;
        } else {
            User user = optUser.get();
            user.getCases();
            user.getRatings();
            return user; 
        } 

    }

}
