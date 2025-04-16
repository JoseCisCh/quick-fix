package com.joecis.quick_fix;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.joecis.quick_fix.DTO.AccountUserDto;
import com.joecis.quick_fix.DTO.RegisterRequest;
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
    void testRegisterUser() {
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

}
