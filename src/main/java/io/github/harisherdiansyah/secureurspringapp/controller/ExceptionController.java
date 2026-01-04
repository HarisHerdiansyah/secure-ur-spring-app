package io.github.harisherdiansyah.secureurspringapp.controller;

import io.github.harisherdiansyah.secureurspringapp.exception.NotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleUnauthorizedException() {
        return "Unauthorized access - custom handler";
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(Exception ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<String> handleExpiredJwtException(Exception ex) {
        return ResponseEntity.status(401).body(ex.getMessage());
    }

    @ExceptionHandler({SignatureException.class, MalformedJwtException.class})
    public ResponseEntity<String> handleTokenValidationException(Exception ex) {
        return ResponseEntity.status(401).body(ex.getMessage());
    }
}
