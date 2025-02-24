package com.sysem.BOTR.service;

import com.sysem.BOTR.constant.ErrorConstant;
import com.sysem.BOTR.models.dto.ListTopic;
import com.sysem.BOTR.models.dto.response.ResponseOutput;
import com.sysem.BOTR.models.entity.Topic;
import com.sysem.BOTR.models.entity.Users;
import com.sysem.BOTR.repository.TopicRepository;
import com.sysem.BOTR.repository.UserRepository;
import com.sysem.BOTR.util.HelperResponseOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import java.util.ArrayList;
import java.util.HashMap;
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


    @Override
    public ResponseOutput getAllTopics() {
        List<ListTopic> listOfTopics = topicRepository.findAll()
                .stream()
                .map(topic -> {
                    ListTopic listTopic = new ListTopic();
                    listTopic.setTopicId(topic.getTopicId());
                    listTopic.setTittle(topic.getTitle());
                    listTopic.setCreatedBy(topic.getUser().getFullname());
                    listTopic.setDescription(topic.getDescription());
                    listTopic.setLikesCount(topic.getLikesCount());
                    listTopic.setDislikeCount(topic.getDislikesCount());
                    return listTopic;
                })
                .collect(Collectors.toList());

        return new ResponseOutput(
                responseOutput.errorSchema(ErrorConstant.REQUEST_SUCCESS),
                listOfTopics
        );
    }



}
