package com.sysem.BOTR.controller;

import com.sysem.BOTR.constant.ReactionType;
import com.sysem.BOTR.models.dto.FeedbackMatric;
import com.sysem.BOTR.models.dto.response.ResponseOutput;
import com.sysem.BOTR.service.AuthServiceImpl;
import com.sysem.BOTR.service.ReactionServiceImpl;
import com.sysem.BOTR.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reactions")
public class ReactionController {

    @Autowired
    private ReactionServiceImpl reactionService;

    @Autowired
    private AuthServiceImpl authService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/vote")
    public ResponseEntity<?> vote(
            @RequestHeader("Authorization") String authHeader,
            @RequestHeader("topicId") Long topicId,
            @RequestHeader("reactionType") String reactionTypeStr
    ) throws  Exception {
        String token = authHeader.substring(7);

        if (authService.isTokenBlacklisted(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token has been revoked. Please log in again.");
        }

        String userEmail = jwtUtil.extractEmail(token);

        ReactionType reactionType;
        try {
            reactionType = ReactionType.valueOf(reactionTypeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid reaction type. Use 'LIKE' or 'DISLIKE'.");
        }

        ResponseOutput responseOutput  = reactionService.addOrUpdateReaction(userEmail, topicId, reactionType);

        return ResponseEntity.ok(responseOutput);
    }

    @GetMapping("/feedback")
    public ResponseEntity<FeedbackMatric> getFeedback(
            @RequestHeader("topicId") Long topicId
    ) {
        FeedbackMatric metrics = reactionService.getTopicFeedback(topicId);
        return ResponseEntity.ok(metrics);
    }
}
