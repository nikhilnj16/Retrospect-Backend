package com.backend.Retrospect.room.controller;


import com.backend.Retrospect.room.entiry.RoomEntity;
import com.backend.Retrospect.room.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class RoomController {

    @Autowired
    IRoomService iRoomService;

    @GetMapping("/get")
    public List<RoomEntity> getRooms(){
         return iRoomService.getAllRooms();
    }
    @PostMapping("/add")
    public List<RoomEntity> setRoom(@RequestBody RoomEntity roomEntity){
        return iRoomService.createRoom(roomEntity);
    }


}
