package dev.tutorial.jwtSecurityDemo.service;

import dev.tutorial.jwtSecurityDemo.dto.RegisterUserDto;
import dev.tutorial.jwtSecurityDemo.entity.User;

public interface UserService {
    User registerUser(RegisterUserDto registerUser);
}
