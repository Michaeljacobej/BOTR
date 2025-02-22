package com.sysem.BOTR.service;

import com.sysem.BOTR.models.entity.Users;
import com.sysem.BOTR.repository.UserRepository;
import com.sysem.BOTR.util.JwtUtil;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(Users user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully!";
    }

    public String loginUser(Users users ) {
        Optional<Users> userOptional = userRepository.findByEmail(users.getEmail());
        if (userOptional.isEmpty() || !passwordEncoder.matches(users.getPassword(), userOptional.get().getPassword())) {
            throw new RuntimeException("Invalid email or password.");
        }

        return jwtUtil.generateToken(users.getEmail());
    }

}
