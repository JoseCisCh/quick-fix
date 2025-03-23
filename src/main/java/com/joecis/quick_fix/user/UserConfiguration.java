package com.joecis.quick_fix.user;

import java.util.LinkedHashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.joecis.quick_fix.rating.Rating;
import com.joecis.quick_fix.usercase.UserCase;

@Configuration
public class UserConfiguration {

    @Bean
    public CommandLineRunner demo(UserRepository userRepository) {
        return (args) -> {
            // Creating users
            User newUser = new User("test@gmail.com", "Test FN", "Test LN", "password");
            newUser.setRatings(new LinkedHashSet<Rating>());
            newUser.setCases(new LinkedHashSet<UserCase>());
            userRepository.save(newUser);

            // Fetching all users
            userRepository.findAll().forEach(user -> {
                System.out.println(user.toString());
            });;
        };
    }
}
