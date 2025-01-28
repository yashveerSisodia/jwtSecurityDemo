package dev.tutorial.jwtSecurityDemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String ERROR_MSG = "{\"error\":";

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(prepareResponseMessage(exception.getMessage()));
    }

    private String prepareResponseMessage(String message) {
        return new StringBuilder().append(ERROR_MSG).append("\"").append(message).append("\"}").toString();
    }
}
