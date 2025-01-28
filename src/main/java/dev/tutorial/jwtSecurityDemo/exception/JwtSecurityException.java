package dev.tutorial.jwtSecurityDemo.exception;

public class JwtSecurityException extends RuntimeException{
    public JwtSecurityException(String message) {
        super(message);
    }
}
