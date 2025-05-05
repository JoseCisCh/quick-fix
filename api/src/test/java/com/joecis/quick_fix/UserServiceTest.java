package com.joecis.quick_fix;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.joecis.quick_fix.DTO.AccountUserDto;
import com.joecis.quick_fix.DTO.RegisterRequest;
import com.joecis.quick_fix.DTO.UserDto;
import com.joecis.quick_fix.role.Role;
import com.joecis.quick_fix.role.RoleRepository;
import com.joecis.quick_fix.user.User;
import com.joecis.quick_fix.user.UserRepository;
import com.joecis.quick_fix.user.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void testRegisterUser_whenValidValuesProvided_shouldReturnExpectedResult() {
        RegisterRequest regRequest = 
                new RegisterRequest("testuser@test.com",
                        "testuser@test.com",
                        "Test",
                        "User",
                        "password");

        User user = new User(
                regRequest.getEmail(),
                regRequest.getUsername(),
                regRequest.getFirstName(),
                regRequest.getLastName(),
                regRequest.getPassword());

        AccountUserDto accountDto =
                new AccountUserDto( "testuser@test.com",
                        "Test",
                        "User");

        when(userRepository.save((any(User.class)))).thenReturn(user);
        when(roleRepository.findByName(anyString())).thenReturn(new Role("ROLE_USER"));
        when(modelMapper.map(any(RegisterRequest.class), eq(User.class)))
            .thenReturn(user);
        when(modelMapper.map(any(User.class), eq(AccountUserDto.class)))
                .thenReturn(accountDto);

        AccountUserDto registeredUser = userService.registerUser(regRequest);

        assertNotNull(registeredUser);
        assertEquals("testuser@test.com", registeredUser.getUsername());
    }

    @Test
    void testGetAllUsers_WhenUsersExistInRepository_shouldReturnListOfUsers() {
        // Arrange
        int usersLength = 3;
        /**
         * Configuring User instances.
         */
        User userOne = new User("userOne@gmail.com", "userOne@gmail.com",
                "User", "One",
                "Password");
        User userTwo = new User("userTwo@gmail.com", "userTwo@gmail.com",
                "User", "Two",
                "Password");
        User userThree = new User("userThree@gmail.com", "userThree@gmail.com",
                "User", "Three",
                "Password");

        /**
         * Configuring UserDto instances.
         */
        UserDto userOneDto =
            new UserDto("userOne@gmail.com", "User", "One");
        UserDto userTwoDto =
            new UserDto("userTwo@gmail.com", "User", "Two");
        UserDto userThreeDto =
            new UserDto("userThree@gmail.com", "User", "Three");

        when(userRepository.findAll())
            .thenReturn(List.of(userOne, userTwo, userThree));
        when(modelMapper.map(any(User.class), eq(UserDto.class)))
            .thenAnswer(invocation -> {
                User user = invocation.getArgument(0);
                System.out.println("Checking for user email " + user.getEmail());
                return new UserDto(user.getEmail(),
                        user.getFirstName(),
                        user.getLastName());
            });
            //.thenReturn(userOneDto, userTwoDto, userThreeDto);

        // Act
        List<UserDto> users = userService.getAllUsers();

        // Assert
        assertNotNull(users);
        assertEquals(users.size(), usersLength);
        assertIterableEquals(
                List.of(userOneDto.getEmail(),
                        userTwoDto.getEmail(),
                        userThreeDto.getEmail()),
                users
                        .stream()
                        .map(user -> { return user.getEmail(); })
                        .collect(Collectors.toList()));
                        
    }

}
