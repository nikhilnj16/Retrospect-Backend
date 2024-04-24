package com.backend.Retrospect.topic.service;

import com.backend.Retrospect.topic.entity.TopicEntity;
import com.backend.Retrospect.user.entity.UserEntity;

import java.util.HashMap;
import java.util.List;

public interface ITopicService {
    HashMap<String, String> addTopic(TopicEntity topicEntity);

    List<TopicEntity> getAllTopic();

}
