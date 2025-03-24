package com.joecis.quick_fix.user;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.joecis.quick_fix.rating.Rating;
import com.joecis.quick_fix.usercase.UserCase;
import com.joecis.quick_fix.usercase.UserCaseStatus;


@Configuration
public class UserConfiguration {

    @Bean
    public CommandLineRunner demo(UserRepository userRepository, UserService userService) {
        return (args) -> {
            // Creating users

            // Creating user 1
            User newUser = new User("test@gmail.com", "Test FN", "Test LN", "password");
            User userOne = newUser;
            LinkedHashSet<Rating> ratings =new LinkedHashSet<Rating>(); 
            LinkedHashSet<UserCase> userCases = new LinkedHashSet<UserCase>();
            userRepository.save(newUser);
            ratings.add(new Rating(3, newUser));
            ratings.add(new Rating(1, newUser));
            ratings.add(new Rating(2, newUser));

            userCases.add(new UserCase("Problem 1", "Problem 1 desc", LocalDateTime.now(), LocalDateTime.now(), newUser, UserCaseStatus.PENDING_TO_ASSIGN_SOLVER));
            userCases.add(new UserCase("Problem 2", "Problem 2 desc", LocalDateTime.now(), LocalDateTime.now(), newUser, UserCaseStatus.PENDING_TO_ASSIGN_SOLVER));
            userCases.add(new UserCase("Problem 3", "Problem 3 desc", LocalDateTime.now(), LocalDateTime.now(), newUser, UserCaseStatus.PENDING_TO_ASSIGN_SOLVER));

            newUser.setRatings(ratings);
            newUser.setCases(userCases);
            userRepository.save(newUser);
            
            // Creating user 2
            newUser = new User("test@gmail.com", "Test FN", "Test LN", "password");
            ratings =new LinkedHashSet<Rating>(); 
            userCases = new LinkedHashSet<UserCase>();
            userRepository.save(newUser);
            ratings.add(new Rating(4, newUser));
            ratings.add(new Rating(5, newUser));
            ratings.add(new Rating(2, newUser));

            userCases.add(new UserCase("Problem 4", "Problem 4 desc", LocalDateTime.now(), LocalDateTime.now(), newUser, userOne,UserCaseStatus.PENDING_TO_ASSIGN_SOLVER));
            userCases.add(new UserCase("Problem 5", "Problem 5 desc", LocalDateTime.now(), LocalDateTime.now(), newUser, UserCaseStatus.PENDING_TO_ASSIGN_SOLVER));
            userCases.add(new UserCase("Problem 6", "Problem 6 desc", LocalDateTime.now(), LocalDateTime.now(), newUser, UserCaseStatus.PENDING_TO_ASSIGN_SOLVER));
            userCases.add(new UserCase("Problem 7", "Problem 7 desc", LocalDateTime.now(), LocalDateTime.now(), newUser, userOne, UserCaseStatus.PENDING_TO_ASSIGN_SOLVER));

            newUser.setRatings(ratings);
            newUser.setCases(userCases);
            userRepository.save(newUser);
        };
    }
}
