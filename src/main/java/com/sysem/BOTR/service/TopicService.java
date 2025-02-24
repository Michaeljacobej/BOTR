package com.sysem.BOTR.service;

import com.sysem.BOTR.models.dto.response.ResponseOutput;
import com.sysem.BOTR.models.entity.Topic;
import com.sysem.BOTR.models.entity.Users;

public interface TopicService {
    ResponseOutput addTopic(String email, Topic topic) throws Exception;

    ResponseOutput getAllTopics()throws Exception;
}
