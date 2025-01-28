package dev.tutorial.jwtSecurityDemo.service;

import dev.tutorial.jwtSecurityDemo.entity.User;
import dev.tutorial.jwtSecurityDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByEmail(username);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User was not found.");
        } else if (!user.get().isActive()) {
            throw new UsernameNotFoundException("User is not a active user.");
        }
        return new org.springframework.security.core.userdetails.User(
                user.get().getEmail(),
                user.get().getPassword(),
                true,
                true,
                true,
                true,
                Collections.singleton(new SimpleGrantedAuthority("USER")));

    }

}
