package dev.tutorial.jwtSecurityDemo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {

    private String email;
    private String password;
}
