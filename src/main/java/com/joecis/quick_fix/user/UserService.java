package com.joecis.quick_fix.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.joecis.quick_fix.DTO.AccountUserDto;
import com.joecis.quick_fix.DTO.RegisterRequest;
import com.joecis.quick_fix.DTO.UserDto;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
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
        userRepository.save(mappedUser);
        AccountUserDto accountUserDto = modelMapper.map(mappedUser, AccountUserDto.class);
        return accountUserDto;
    }

}
