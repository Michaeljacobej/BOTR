package com.sysem.BOTR.models.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ListTopic {
    private Long topicId;
    private String tittle;
    private String description;

    private String createdBy;
    private int likesCount;

    private int dislikeCount;
}
