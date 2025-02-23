package com.sysem.BOTR.controller;

import com.sysem.BOTR.models.dto.response.ResponseOutput;
import com.sysem.BOTR.models.entity.Topic;
import com.sysem.BOTR.service.AuthServiceImpl;
import com.sysem.BOTR.service.TopicServiceImpl;
import com.sysem.BOTR.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    @Autowired
    private TopicServiceImpl topicService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthServiceImpl authService;

    @PostMapping("/add")
    public ResponseEntity<?> addTopic(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody Topic topic) throws  Exception {

        String token = authHeader.substring(7);
        if (authService.isTokenBlacklisted(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token has been revoked. Please log in again.");
        }
        String email = jwtUtil.extractEmail(token);

        ResponseOutput responseOutput = topicService.addTopic(email, topic);
        return ResponseEntity.ok(responseOutput);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Topic>> getAllTopics() {
        return ResponseEntity.ok(topicService.getAllTopics());
    }
}