package com.backend.Retrospect.topic.controller;

import com.backend.Retrospect.topic.entity.TopicEntity;
import com.backend.Retrospect.topic.service.TopicServiceImpl;
import com.backend.Retrospect.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/topic")
@CrossOrigin("*")
public class TopicController {
    @Autowired
    private TopicServiceImpl service;

    @PostMapping("/add")
    public HashMap<String, String> addTopic(@RequestBody TopicEntity topicEntity){
        return service.addTopic(topicEntity);
    }

    @GetMapping("/getAllTopic")
    public List<TopicEntity> getAllTopic(){
        return service.getAllTopic();
    }
}
