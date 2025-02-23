package com.sysem.BOTR.service;

import com.sysem.BOTR.constant.ErrorConstant;
import com.sysem.BOTR.models.dto.response.ResponseOutput;
import com.sysem.BOTR.models.entity.Topic;
import com.sysem.BOTR.models.entity.Users;
import com.sysem.BOTR.repository.TopicRepository;
import com.sysem.BOTR.repository.UserRepository;
import com.sysem.BOTR.util.HelperResponseOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TopicServiceImpl implements  TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HelperResponseOutput responseOutput;


    @Override
    public ResponseOutput addTopic(String email, Topic topic) throws Exception {
        Users user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        topic.setUser(user);
        topic.setTitle(topic.getTitle());
        topic.setDescription(topic.getDescription());
        topicRepository.save(topic);
        return new ResponseOutput(
                responseOutput.errorSchema(ErrorConstant.REQUEST_SUCCESS), "add Topic Success!");
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }
}
