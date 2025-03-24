package com.joecis.quick_fix.user;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.joecis.quick_fix.DTO.UserDto;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        try {
            UserDto foundUser = userService.getById(id);
            return ResponseEntity.ok().body(foundUser);
        } catch(UserNotFoundException e) {
            return ResponseEntity.badRequest().body(null);
        }
    } 

    @GetMapping("users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("users")
    public ResponseEntity<User> createUser(@RequestBody User body) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(body));
        } catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
