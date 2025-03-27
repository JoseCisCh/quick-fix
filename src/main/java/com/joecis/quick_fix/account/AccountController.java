package com.joecis.quick_fix.account;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.joecis.quick_fix.DTO.AccountUserDto;
import com.joecis.quick_fix.DTO.LoginRequest;
import com.joecis.quick_fix.DTO.RegisterRequest;
import com.joecis.quick_fix.user.UserAlreadyExistsException;
import com.joecis.quick_fix.user.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AccountController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
    private SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
    
    public AccountController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
        SecurityContext context = securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(authenticationResponse);
        securityContextHolderStrategy.setContext(context);
        securityContextRepository.saveContext(context, request, response);
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<AccountUserDto> register(@RequestBody RegisterRequest registerRequest) throws UserAlreadyExistsException {
        if(userService.getByUsername(registerRequest.getUsername()) != null) {
            throw new UserAlreadyExistsException( registerRequest.getUsername());
        } 

        AccountUserDto accountDto = userService.registerUser(registerRequest);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(accountDto);
    }
}
