package com.joecis.quick_fix.user;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.joecis.quick_fix.DTO.AccountUserDto;
import com.joecis.quick_fix.DTO.RegisterRequest;
import com.joecis.quick_fix.DTO.UserDto;
import com.joecis.quick_fix.role.Role;
import com.joecis.quick_fix.role.RoleRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    public UserService(UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }
    
    @Transactional
    public UserDto getById(Long id) {
        Optional<User> optUser = userRepository.findById(id);    
        if(optUser.isPresent()) {
            User user = optUser.get();
            return  modelMapper.map(user, UserDto.class);
        } else {
            throw new UserNotFoundException(id);
        }
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
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

    @Transactional
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserDto> usersDto = users.stream()
                                        .map(user -> {
                                            return modelMapper.map(user, UserDto.class);
                                        })
                                        .collect(Collectors.toList());

        return usersDto;
    }

    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }

    public AccountUserDto registerUser(RegisterRequest registerInfo) {
        User mappedUser = modelMapper.map(registerInfo, User.class);
        mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
        Role basicUserRole = roleRepository.findByName("ROLE_USER");
        LinkedHashSet<Role> roles = new LinkedHashSet<Role>();
        roles.add(basicUserRole);
        mappedUser.setAuthorities(roles);
        mappedUser.setEnabled(true);
        mappedUser.setAccountNonLocked(true);
        mappedUser.setCredentialsNonExpired(true);
        mappedUser.setAccountNonExpired(true);
        userRepository.save(mappedUser);
        AccountUserDto accountUserDto = modelMapper.map(mappedUser, AccountUserDto.class);
        return accountUserDto;
    }

}
