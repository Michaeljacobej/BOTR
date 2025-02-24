package com.sysem.BOTR.service;

import com.sysem.BOTR.constant.ErrorConstant;
import com.sysem.BOTR.constant.ReactionType;
import com.sysem.BOTR.models.dto.FeedbackMatric;
import com.sysem.BOTR.models.dto.response.ResponseOutput;
import com.sysem.BOTR.models.entity.Reaction;
import com.sysem.BOTR.models.entity.Topic;
import com.sysem.BOTR.models.entity.Users;
import com.sysem.BOTR.repository.ReactionRepository;
import com.sysem.BOTR.repository.TopicRepository;
import com.sysem.BOTR.repository.UserRepository;
import com.sysem.BOTR.util.HelperResponseOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ReactionServiceImpl implements  ReactionService{

    @Autowired
    private ReactionRepository reactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private HelperResponseOutput responseOutput;

    private static final int MAX_VOTES_PER_DAY = 5;

    @Override
    public ResponseOutput addOrUpdateReaction(String userEmail, Long topicId, ReactionType newReactionType) throws Exception {
        Users user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        Optional<Reaction> existingReaction = reactionRepository.findByUserAndTopic(user, topic);
        long todayVotes = reactionRepository.countTodayVotes(user, LocalDate.now());


        if (existingReaction.isPresent()) {
            Reaction reaction = existingReaction.get();

            if (reaction.getReactionType() == newReactionType) {
                reactionRepository.delete(reaction);
                updateTopicMetrics(topic);
                return new ResponseOutput(
                        responseOutput.errorSchema(ErrorConstant.REQUEST_SUCCESS), "Reaction removed.");
            } else {
                reaction.setReactionType(newReactionType);
                reaction.setReactionDate(LocalDate.now());
                reactionRepository.save(reaction);
                updateTopicMetrics(topic);
                return new ResponseOutput(
                        responseOutput.errorSchema(ErrorConstant.REQUEST_SUCCESS), "Reaction updated.");
            }
        }


        if (todayVotes >= MAX_VOTES_PER_DAY) {
            return new ResponseOutput(
                    responseOutput.errorSchema(ErrorConstant.REQUEST_SUCCESS), "Vote limit exceeded for today.");
        }

        // Otherwise, allow voting for a new topic
        Reaction newReaction = new Reaction();
        newReaction.setUser(user);
        newReaction.setTopic(topic);
        newReaction.setReactionType(newReactionType);
        newReaction.setReactionDate(LocalDate.now());
        reactionRepository.save(newReaction);

        updateTopicMetrics(topic);

        return new ResponseOutput(
                responseOutput.errorSchema(ErrorConstant.REQUEST_SUCCESS), "Reaction added.");
    }


    private void updateTopicMetrics(Topic topic) {
        long likes = reactionRepository.countLikesByTopic(topic);
        long dislikes = reactionRepository.countDislikesByTopic(topic);
        topic.setLikesCount((int) likes);
        topic.setDislikesCount((int) dislikes);
        topicRepository.save(topic);
    }

    public FeedbackMatric getTopicFeedback(Long topicId) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        int likes = topic.getLikesCount();
        int dislikes = topic.getDislikesCount();
        int total = likes + dislikes;

        double likePercentage = (total == 0) ? 0 : ((double) likes / total) * 100;
        double dislikePercentage = (total == 0) ? 0 : ((double) dislikes / total) * 100;

        return new FeedbackMatric(topicId, likes, dislikes, likePercentage, dislikePercentage);
    }

}
