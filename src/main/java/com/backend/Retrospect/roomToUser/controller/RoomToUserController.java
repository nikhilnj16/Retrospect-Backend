package com.backend.Retrospect.roomToUser.controller;

import com.backend.Retrospect.roomToUser.dto.UserRoomJoinDTO;
import com.backend.Retrospect.roomToUser.entity.RoomToUserId;
import com.backend.Retrospect.roomToUser.service.RoomToUserService;
import com.backend.Retrospect.topic.entity.TopicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin("*")

public class RoomToUserController {

    @Autowired
    private RoomToUserService service;

    @PostMapping("/userJoinRoom")
    public HashMap<String, String> userJoinRoom(@RequestBody UserRoomJoinDTO userRoomJoinDTO){
        return service.UserJoinedRoom(userRoomJoinDTO);
    }

}
