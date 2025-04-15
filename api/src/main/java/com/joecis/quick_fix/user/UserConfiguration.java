package com.joecis.quick_fix.user;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.joecis.quick_fix.notification.Notification;
import com.joecis.quick_fix.notification.NotificationStatus;
import com.joecis.quick_fix.notification.NotificationType;
import com.joecis.quick_fix.rating.Rating;
import com.joecis.quick_fix.role.Role;
import com.joecis.quick_fix.role.RoleRepository;
import com.joecis.quick_fix.usercase.UserCase;
import com.joecis.quick_fix.usercase.UserCaseStatus;


@Configuration
public class UserConfiguration {

    @Bean
    public CommandLineRunner demo(UserRepository userRepository,
                                  UserService userService,
                                  RoleRepository roleRepository) {
        return (args) -> {

            // Initializing Bcrypt instance.

            BCryptPasswordEncoder passwordEncoder =
                     new BCryptPasswordEncoder(10);
            // Creating user roles/Authorities.
            //
            Role adminUser = new Role("ROLE_ADMIN");
            Role normalUser = new Role("ROLE_USER");
            Set<Role> roles = new LinkedHashSet<Role>();
            roles.add(adminUser);
            roles.add(normalUser);
            roleRepository.saveAll(roles);

            // Creating users

            // Creating user 1
            User newUser = new User("test@gmail.com",
                                    "test@gmail.com",
                                    "Test FN",
                                    "Test LN",
                                    passwordEncoder.encode("password"),
                                    true,
                                    true,
                                    true,
                                    true);

            User userOne = newUser;
            LinkedHashSet<Rating> ratings =
                    new LinkedHashSet<Rating>(); 
            LinkedHashSet<UserCase> userCases =
                    new LinkedHashSet<UserCase>();
            LinkedHashSet<Notification> notifications =
                    new LinkedHashSet<Notification>();

            userRepository.save(newUser);
            ratings.add(new Rating(3, newUser));
            ratings.add(new Rating(1, newUser));
            ratings.add(new Rating(2, newUser));

            userCases.add(new UserCase(
                    "Problem 1",
                    "Problem 1 desc",
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    newUser,
                    UserCaseStatus.PENDING_TO_ASSIGN_SOLVER));

            userCases.add(new UserCase(
                    "Problem 2",
                    "Problem 2 desc",
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    newUser,
                    UserCaseStatus.PENDING_TO_ASSIGN_SOLVER));

            userCases.add(new UserCase(
                    "Problem 3",
                    "Problem 3 desc",
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    newUser,
                    UserCaseStatus.PENDING_TO_ASSIGN_SOLVER));

            roles.add(adminUser);
            roles.add(normalUser);

            newUser.setRatings(ratings);
            newUser.setCases(userCases);
            newUser.setAuthorities(roles);
            userRepository.save(newUser);

            System.out.println("Passed user 1 creation");
            // Creating user 2
            newUser = new User("test_2@gmail.com",
                               "test_2@gmail.com",
                               "Test 2 FN",
                               "Test 2 LN",
                               passwordEncoder.encode("password"),
                               true,
                               true,
                               true,
                               true);

            ratings =new LinkedHashSet<Rating>(); 
            userCases = new LinkedHashSet<UserCase>();
            roles = new LinkedHashSet<Role>();

            userRepository.save(newUser);
            ratings.add(new Rating(4, newUser));
            ratings.add(new Rating(5, newUser));
            ratings.add(new Rating(2, newUser));

            userCases.add(new UserCase(
                    "Problem 4",
                    "Problem 4 desc",
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    newUser,
                    userOne,
                    UserCaseStatus.PENDING_TO_ASSIGN_SOLVER));

            userCases.add(new UserCase(
                    "Problem 5",
                    "Problem 5 desc",
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    newUser,
                    UserCaseStatus.PENDING_TO_ASSIGN_SOLVER));


            userCases.add(new UserCase(
                     "Problem 6",
                     "Problem 6 desc",
                     LocalDateTime.now(),
                     LocalDateTime.now(),
                     newUser,
                     UserCaseStatus.PENDING_TO_ASSIGN_SOLVER));

            userCases.add(new UserCase(
                     "Problem 7",
                     "Problem 7 desc",
                     LocalDateTime.now(),
                     LocalDateTime.now(),
                     newUser,
                     userOne,
                     UserCaseStatus.PENDING_TO_ASSIGN_SOLVER));

            roles.add(normalUser);

            newUser.setRatings(ratings);
            newUser.setCases(userCases);
            newUser.setAuthorities(roles);
            userRepository.save(newUser);

            System.out.println("Passed user 2 creation");

            // Creating user 3
            newUser = new User(
                    "test_3@gmail.com",
                    "test_3@gmail.com",
                    "Test 3 FN",
                    "Test 3 LN",
                    passwordEncoder.encode("password"),
                    true,
                    true,
                    true,
                    true);

            ratings =new LinkedHashSet<Rating>(); 
            userCases = new LinkedHashSet<UserCase>();
            roles = new LinkedHashSet<Role>();
            notifications = new LinkedHashSet<Notification>(); 

            userRepository.save(newUser);
            ratings.add(new Rating(4, newUser));
            ratings.add(new Rating(5, newUser));
            ratings.add(new Rating(2, newUser));

            UserCase tempUserCase = new UserCase(
                    "Problem 8",
                    "Problem 8 desc",
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    newUser,
                    userOne,
                    UserCaseStatus.PENDING_TO_ASSIGN_SOLVER);

            userCases.add(tempUserCase);
            userCases.add(new UserCase(
                    "Problem 9",
                    "Problem 9 desc",
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    newUser,
                    userOne,
                    UserCaseStatus.PENDING_TO_ASSIGN_SOLVER));
            userCases.add(new UserCase(
                     "Problem 10",
                     "Problem 10 desc",
                     LocalDateTime.now(),
                     LocalDateTime.now(),
                     newUser,
                     UserCaseStatus.PENDING_TO_ASSIGN_SOLVER));
            userCases.add(new UserCase(
                    "Problem 11",
                    "Problem 11 desc",
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    newUser,
                    UserCaseStatus.PENDING_TO_ASSIGN_SOLVER));

            userCases.add(new UserCase(
                    "Problem 12",
                    "Problem 12 desc",
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    newUser,
                    userOne,
                    UserCaseStatus.PENDING_TO_ASSIGN_SOLVER));

            roles.add(normalUser);

            notifications.add(
                    new Notification(
                            NotificationType.SOLVER_REQUEST,
                            NotificationStatus.UNCHECKED,
                            LocalDateTime.now(),
                            userOne,
                            newUser,
                            tempUserCase));

            newUser.setRatings(ratings);
            newUser.setCases(userCases);
            newUser.setNotifications(notifications);
            newUser.setAuthorities(roles);
            userRepository.save(newUser);
            System.out.println("Passed user 3 creation");
        };
    }
}
