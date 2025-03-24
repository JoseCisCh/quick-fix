package com.joecis.quick_fix.user;

import java.util.LinkedHashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.joecis.quick_fix.rating.Rating;


@Configuration
public class UserConfiguration {

    @Bean
    public CommandLineRunner demo(UserRepository userRepository, UserService userService) {
        return (args) -> {
            // Creating users
            User newUser = new User("test@gmail.com", "Test FN", "Test LN", "password");
            LinkedHashSet<Rating> ratings =new LinkedHashSet<Rating>(); 
            userRepository.save(newUser);
            ratings.add(new Rating(3, newUser));
            newUser.setRatings(ratings);
            userRepository.save(newUser);
            
        };
    }
}
