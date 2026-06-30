package com.foodg.security.userdetails;

import com.foodg.security.jwt.JwtService;
import com.foodg.user.entity.User;
import com.foodg.user.repository.UserRepository;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public boolean findUserEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}