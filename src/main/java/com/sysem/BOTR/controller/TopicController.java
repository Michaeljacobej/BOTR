package com.sysem.BOTR.controller;

import com.sysem.BOTR.models.entity.Topic;
import com.sysem.BOTR.service.TopicService;
import com.sysem.BOTR.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/add")
    public ResponseEntity<Topic> addTopic(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody Topic topic) {

        String token = authHeader.substring(7);
        String email = jwtUtil.extractEmail(token);

        Topic newTopic = topicService.addTopic(email, topic);
        return ResponseEntity.ok(newTopic);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Topic>> getAllTopics() {
        return ResponseEntity.ok(topicService.getAllTopics());
    }
}