package com.backend.Retrospect.roomToUser.controller;

import com.backend.Retrospect.roomToUser.entity.RoomToUserId;
import com.backend.Retrospect.roomToUser.service.RoomToUserService;
import com.backend.Retrospect.topic.entity.TopicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/roomToUser")
public class RoomToUserController {

    @Autowired
    private RoomToUserService service;
    @PostMapping("/add")
    public HashMap<String, String> addRoomToUser(@RequestBody RoomToUserId roomToUserId){
        return service.addRoomToUser(roomToUserId);
    }

}
