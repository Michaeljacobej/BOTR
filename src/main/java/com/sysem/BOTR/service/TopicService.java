package com.sysem.BOTR.service;

import com.sysem.BOTR.models.entity.Topic;
import com.sysem.BOTR.models.entity.Users;
import com.sysem.BOTR.repository.TopicRepository;
import com.sysem.BOTR.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    public Topic addTopic(String email, Topic topic) {
        Users user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        topic.setUser(user);
        topic.setTitle(topic.getTitle());
        topic.setDescription(topic.getDescription());
        return topicRepository.save(topic);
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }
}
