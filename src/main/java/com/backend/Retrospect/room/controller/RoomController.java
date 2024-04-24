package com.backend.Retrospect.room.controller;

import com.backend.Retrospect.room.entity.RoomEntity;
import com.backend.Retrospect.room.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private IRoomService roomService;

    @GetMapping("/getAllRooms")
    public List<RoomEntity> getallRooms(){
        return roomService.getAllRooms();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public List<RoomEntity> createroom(@RequestBody RoomEntity roomEntity){
        return roomService.createroom(roomEntity);
    }

}
