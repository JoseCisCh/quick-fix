package com.joecis.quick_fix.account;

import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.joecis.quick_fix.DTO.AccountUserDto;
import com.joecis.quick_fix.DTO.LoginRequest;
import com.joecis.quick_fix.DTO.RegisterRequest;
import com.joecis.quick_fix.jwt.TokenService;
import com.joecis.quick_fix.role.Role;
import com.joecis.quick_fix.user.User;
import com.joecis.quick_fix.user.UserAlreadyExistsException;
import com.joecis.quick_fix.user.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
public class AccountController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;
    
    public AccountController(
            AuthenticationManager authenticationManager,
            UserService userService,
            TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<AccountUserDto> login(
            @RequestBody @Valid LoginRequest loginRequest,
            HttpServletRequest request,
            HttpServletResponse response) {

        Authentication authenticationRequest = 
                UsernamePasswordAuthenticationToken.unauthenticated(
                        loginRequest.getUsername(),
                        loginRequest.getPassword());

        Authentication authenticationResponse = 
                this.authenticationManager.authenticate(authenticationRequest);

        // NOTE: After testing this if statement may not be necessary, 
        // however I will keep it just for consistency.
        if(authenticationResponse.isAuthenticated()) {
            Predicate<GrantedAuthority> isRole = authority ->
                    authority instanceof Role;

            Function<GrantedAuthority, Role> castToRole = authority ->
                    (Role) authority;

            User  user = (User) authenticationResponse.getPrincipal();
            Set<Role> authorities =  authenticationResponse
                                        .getAuthorities()
                                        .stream()
                                        .filter(isRole)
                                        .map(castToRole)
                                        .collect(Collectors.toSet());

            AccountUserDto accountDto = new AccountUserDto();
            accountDto.setId(user.getId());
            accountDto.setUsername(user.getUsername());
            accountDto.setAuthorities(authorities);
            accountDto.setToken(
                    tokenService.generateToken(
                         accountDto.getId(),
                         authorities));
            accountDto.setFirstName(user.getFirstName());
            accountDto.setLastName(user.getLastName());

            return ResponseEntity.status(HttpStatus.OK).body(accountDto);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AccountUserDto> register(
            @RequestBody @Valid RegisterRequest registerRequest)
            throws UserAlreadyExistsException {
        if(userService.getByUsername(registerRequest.getUsername()) 
                != null) {
            throw new UserAlreadyExistsException( 
                    registerRequest.getUsername());
        } 
        
        AccountUserDto accountDto = userService
                .registerUser(registerRequest);

        String token = tokenService.generateToken(
                accountDto.getId(),
                accountDto.getAuthorities());

        accountDto.setToken(token);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(accountDto);
    }
}
