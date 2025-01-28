package dev.tutorial.jwtSecurityDemo.controller;

import dev.tutorial.jwtSecurityDemo.dto.LoginDto;
import dev.tutorial.jwtSecurityDemo.dto.RegisterUserDto;
import dev.tutorial.jwtSecurityDemo.entity.User;
import dev.tutorial.jwtSecurityDemo.service.UserService;
import dev.tutorial.jwtSecurityDemo.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private static final String ERROR_INVALID_EMAIL_PASSWORD = "{\"error\": \"Invalid email or password\"}";

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@Valid @RequestBody LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
            );
            return new ResponseEntity<>(jwtUtil.generateToken(authentication.getName()), HttpStatus.OK);
        } catch (BadCredentialsException exception) {
            log.error("Invalid Email or password");
            return new ResponseEntity<>(ERROR_INVALID_EMAIL_PASSWORD, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(value = "/register-user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> registerUser(@Valid @RequestBody RegisterUserDto registerUser) {
            return new ResponseEntity<>(userService.registerUser(registerUser), HttpStatus.OK);
    }

}
