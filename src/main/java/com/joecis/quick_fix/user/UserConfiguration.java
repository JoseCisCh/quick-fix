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

            // Creating user 1
            User newUser = new User("test@gmail.com", "Test FN", "Test LN", "password");
            LinkedHashSet<Rating> ratings =new LinkedHashSet<Rating>(); 
            userRepository.save(newUser);
            ratings.add(new Rating(3, newUser));
            ratings.add(new Rating(1, newUser));
            ratings.add(new Rating(2, newUser));
            newUser.setRatings(ratings);
            userRepository.save(newUser);
            
            // Creating user 2
            newUser = new User("test@gmail.com", "Test FN", "Test LN", "password");
            ratings =new LinkedHashSet<Rating>(); 
            userRepository.save(newUser);
            ratings.add(new Rating(4, newUser));
            ratings.add(new Rating(5, newUser));
            ratings.add(new Rating(2, newUser));
            newUser.setRatings(ratings);
            userRepository.save(newUser);
        };
    }
}
