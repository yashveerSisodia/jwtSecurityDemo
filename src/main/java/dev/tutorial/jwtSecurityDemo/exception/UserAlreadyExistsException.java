package dev.tutorial.jwtSecurityDemo.exception;

public class UserAlreadyExistsException extends JwtSecurityException{
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
