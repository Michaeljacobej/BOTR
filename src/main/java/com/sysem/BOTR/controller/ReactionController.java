package com.sysem.BOTR.controller;

import com.sysem.BOTR.constant.ReactionType;
import com.sysem.BOTR.models.dto.FeedbackMatric;
import com.sysem.BOTR.service.ReactionService;
import com.sysem.BOTR.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reactions")
public class ReactionController {

    @Autowired
    private ReactionService reactionService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/vote")
    public ResponseEntity<String> vote(
            @RequestHeader("Authorization") String authHeader,
            @RequestHeader("topicId") Long topicId,
            @RequestHeader("reactionType") String reactionTypeStr
    ) {
        String token = authHeader.substring(7);
        String userEmail = jwtUtil.extractEmail(token);

        ReactionType reactionType;
        try {
            reactionType = ReactionType.valueOf(reactionTypeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid reaction type. Use 'LIKE' or 'DISLIKE'.");
        }

        String response = reactionService.addOrUpdateReaction(userEmail, topicId, reactionType);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/feedback")
    public ResponseEntity<FeedbackMatric> getFeedback(
            @RequestHeader("topicId") Long topicId
    ) {
        FeedbackMatric metrics = reactionService.getTopicFeedback(topicId);
        return ResponseEntity.ok(metrics);
    }
}
