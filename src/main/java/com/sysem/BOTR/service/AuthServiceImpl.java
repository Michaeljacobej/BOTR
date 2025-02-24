package com.sysem.BOTR.service;

import com.sysem.BOTR.constant.ErrorConstant;
import com.sysem.BOTR.models.dto.response.ResponseOutput;
import com.sysem.BOTR.models.entity.Users;
import com.sysem.BOTR.repository.UserRepository;
import com.sysem.BOTR.util.HelperResponseOutput;
import com.sysem.BOTR.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HelperResponseOutput responseOutput;

    private static final ConcurrentHashMap<String, Long> tokenBlacklist = new ConcurrentHashMap<>();

    @Override
    public ResponseOutput loginUser(Users users) throws Exception {
        if (users == null || users.getEmail() == null || users.getEmail().trim().isEmpty()) {
            return new ResponseOutput(responseOutput.errorSchema(ErrorConstant.EMAIL_REQUIRED), null);
        }
        if (users.getPassword() == null || users.getPassword().trim().isEmpty()) {
            return new ResponseOutput(responseOutput.errorSchema(ErrorConstant.PASSWORD_REQUIRED), null);
        }

        // Check if email exists in database
        Optional<Users> userOptional = userRepository.findByEmail(users.getEmail());
        if (userOptional.isEmpty()) {
            return new ResponseOutput(responseOutput.errorSchema(ErrorConstant.EMAIL_NOT_FOUND), null);
        }

        // Validate password
        Users user = userOptional.get();
        if (!passwordEncoder.matches(users.getPassword(), user.getPassword())) {
            return new ResponseOutput(responseOutput.errorSchema(ErrorConstant.INCORRECT_PASSWORD), null);
        }

        return new ResponseOutput(
                responseOutput.errorSchema(ErrorConstant.REQUEST_SUCCESS),  jwtUtil.generateToken(users.getEmail()));
    }

    @Override
    public ResponseOutput registerUser(Users user) throws Exception {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return new ResponseOutput(responseOutput.errorSchema(ErrorConstant.EMAIL_HAVE_BEEN_REGISTERED), null);

        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseOutput(
                responseOutput.errorSchema(ErrorConstant.REQUEST_SUCCESS), "User registered successfully!");

    }
    @Override
    public ResponseOutput logoutUser(String token) {
        if (token == null || !jwtUtil.validateToken(token)) {
            return new ResponseOutput(responseOutput.errorSchema(ErrorConstant.INVALID_TOKEN), "Invalid or missing token.");
        }


        long expirationTime = jwtUtil.extractExpiration(token).getTime();
        tokenBlacklist.put(token,expirationTime);
        return new ResponseOutput(responseOutput.errorSchema(ErrorConstant.REQUEST_SUCCESS), "Logout successful.");
    }

    public boolean isTokenBlacklisted(String token) {
        if (token == null) return false;

        Long expirationTime = tokenBlacklist.get(token);
        if (expirationTime == null) return false;

        if (System.currentTimeMillis() > expirationTime) {
            tokenBlacklist.remove(token);
            return false;
        }

        return true;
    }


}
