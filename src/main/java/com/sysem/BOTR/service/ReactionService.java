package com.sysem.BOTR.service;

import com.sysem.BOTR.constant.ReactionType;
import com.sysem.BOTR.models.dto.response.ResponseOutput;
import com.sysem.BOTR.models.entity.Users;

public interface ReactionService {
    ResponseOutput addOrUpdateReaction(String userEmail, Long topicId, ReactionType newReactionType) throws Exception;
}
