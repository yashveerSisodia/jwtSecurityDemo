package dev.tutorial.jwtSecurityDemo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterUserDto {

    @NotBlank(message = "first name can't be null")
    private String firstName;

    private String lastName;

    @Email(message = "email can't be null or invalid")
    private String email;

    @NotBlank(message = "password can't be null")
    private String password;

}
