package com.backend.Retrospect.topic.service;

import com.backend.Retrospect.topic.entity.TopicEntity;
import com.backend.Retrospect.topic.repository.ITopicRepository;
import com.backend.Retrospect.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class TopicServiceImpl implements ITopicService{

    @Autowired
    private ITopicRepository repository;

    @Override
    public HashMap<String, String> addTopic(TopicEntity topicEntity) {
        repository.save(topicEntity);
        HashMap<String, String> response = new HashMap<>();
        response.put("Status" , "OK");
        return response;
    }

    @Override
    public List<TopicEntity> getAllTopic() {
        return repository.findAll();
    }
}
