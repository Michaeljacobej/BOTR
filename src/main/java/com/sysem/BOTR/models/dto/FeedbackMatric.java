package com.sysem.BOTR.models.dto;

public class FeedbackMatric{
    private Long topicId;
    private int likes;
    private int dislikes;
    private double likePercentage;
    private double dislikePercentage;

    public FeedbackMatric(Long topicId, int likes, int dislikes, double likePercentage, double dislikePercentage) {
        this.topicId = topicId;
        this.likes = likes;
        this.dislikes = dislikes;
        this.likePercentage = likePercentage;
        this.dislikePercentage = dislikePercentage;
    }

}