package com.joecis.quick_fix.user;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.joecis.quick_fix.DTO.UserDto;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }
    
    public UserDto getById(Long id) {
        Optional<User> optUser = userRepository.findById(id);    
        if(optUser.isPresent()) {
            User user = optUser.get();

            return  modelMapper.map(user, UserDto.class);
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

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }

}
