package com.java.cidade.config.exception;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.ServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ResourceExceptionHandle {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> exceptionsError(Exception e, ServletRequest servletRequest) {
        StandardError standardError = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<StandardError> expiredError(ExpiredJwtException e, ServletRequest servletRequest) {
        StandardError standardError = new StandardError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(standardError);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<StandardError> credentialError(BadCredentialsException e, ServletRequest servletRequest) {
        StandardError standardError = new StandardError(HttpStatus.FORBIDDEN.value(), "E-mail ou senha inválidos. Por favor, verifique e tente novamente.");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(standardError);
    }
}
