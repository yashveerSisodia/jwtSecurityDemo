package dev.tutorial.jwtSecurityDemo.service.impl;

import dev.tutorial.jwtSecurityDemo.dto.RegisterUserDto;
import dev.tutorial.jwtSecurityDemo.entity.User;
import dev.tutorial.jwtSecurityDemo.exception.UserAlreadyExistsException;
import dev.tutorial.jwtSecurityDemo.repository.UserRepository;
import dev.tutorial.jwtSecurityDemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(RegisterUserDto registerUser) {
        // check if email already exists in db.
        if (userRepository.findByEmail(registerUser.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User with email: "+ registerUser.getEmail() +" already exists.");
        }
        User user = new User();
        user.setFirstName(registerUser.getFirstName());
        user.setLastName(registerUser.getLastName());

        if (registerUser.getLastName() == null || registerUser.getLastName().isEmpty()) {
            user.setUserName(registerUser.getFirstName());
        } else {
            user.setUserName(registerUser.getFirstName().concat(" ").concat(registerUser.getLastName()));
        }
        user.setEmail(registerUser.getEmail());
        user.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        return userRepository.save(user);
    }
}
