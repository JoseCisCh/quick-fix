package com.joecis.quick_fix.errorhandling;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.joecis.quick_fix.user.UserAlreadyExistsException;

@ControllerAdvice(annotations = RestController.class)
public class ControllerErrorHandler {

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handle() {
        return ResponseEntity.internalServerError().body("Internal server error");
    }

    @ExceptionHandler(UserAlreadyExistsException.class) 
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class) 
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
    
}
