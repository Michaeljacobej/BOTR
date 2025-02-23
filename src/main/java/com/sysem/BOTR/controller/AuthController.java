package com.sysem.BOTR.controller;

import com.sysem.BOTR.models.dto.response.ResponseOutput;
import com.sysem.BOTR.models.entity.Users;
import com.sysem.BOTR.service.AuthService;
import com.sysem.BOTR.service.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users user)  throws  Exception {
        ResponseOutput responseOutput = authService.registerUser(user);
        return ResponseEntity.ok(responseOutput);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user)  throws  Exception {
        ResponseOutput responseOutput = authService.loginUser(user);
        return ResponseEntity.ok(responseOutput);
}


    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader)  throws  Exception {
        ResponseOutput responseOutput = authService.logoutUser(authHeader);
        return ResponseEntity.ok(responseOutput);
    }

}
