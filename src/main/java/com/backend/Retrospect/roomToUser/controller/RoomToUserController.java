package com.backend.Retrospect.roomToUser.controller;

import com.backend.Retrospect.roomToUser.dto.UserRoomJoinDTO;
import com.backend.Retrospect.roomToUser.dto.UsersInRoom;
import com.backend.Retrospect.roomToUser.entity.RoomToUserEntity;
import com.backend.Retrospect.roomToUser.entity.RoomToUserId;
import com.backend.Retrospect.roomToUser.service.RoomToUserService;
import com.backend.Retrospect.topic.entity.TopicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController("/RoomToUserController")
@CrossOrigin("*")

public class RoomToUserController {

    @Autowired
    private RoomToUserService service;

    @PostMapping("/userJoinRoom")
    public HashMap<String, String> userJoinRoom(@RequestBody UserRoomJoinDTO userRoomJoinDTO){
        return service.UserJoinedRoom(userRoomJoinDTO);
    }
    @GetMapping("/usersInRoom/{roomId}")
    public List<UsersInRoom> getUsersInRoom(@PathVariable String roomId) {
        List<RoomToUserEntity> roomUsers = service.usersInRoom(roomId);
        List<UsersInRoom> usersDTO = new ArrayList<>();

        for (RoomToUserEntity roomUser : roomUsers) {
            UsersInRoom userDTO = new UsersInRoom();
            userDTO.setUserId(roomUser.getId().getUserEntity().getUserId());
            userDTO.setUserName(roomUser.getId().getUserEntity().getUserName());
            userDTO.setUserEmail(roomUser.getId().getUserEntity().getUserEmail());
            usersDTO.add(userDTO);
        }

        return usersDTO;
    }

}
