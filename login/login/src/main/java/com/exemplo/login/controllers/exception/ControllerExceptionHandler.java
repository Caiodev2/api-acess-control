package com.exemplo.login.controllers.exception;

import com.exemplo.login.services.exceptions.ResourceNotFoundException;
import com.exemplo.login.services.exceptions.EmailAlreadyEcistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant
                .now()
                ,status.value()
                ,e.getMessage()
                ,request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(EmailAlreadyEcistsException.class)
    public ResponseEntity<StandardError> emailAlreadyExists(
            EmailAlreadyEcistsException e,
            HttpServletRequest request) {

        String error = "Email already exists";
        HttpStatus status = HttpStatus.CONFLICT;

        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }
}
