package com.backend.Retrospect.room.controller;


import com.backend.Retrospect.room.entity.RoomEntity;
import com.backend.Retrospect.room.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin("*")
public class RoomController {

    @Autowired
    IRoomService iRoomService;

    @GetMapping("/get")
    public List<RoomEntity> getRooms(@RequestHeader String token){
         return iRoomService.getAllRooms(token);
    }
    @PostMapping("/add")
    public HashMap<String, String> setRoom(@RequestBody RoomEntity roomEntity){
        return iRoomService.createRoom(roomEntity);
    }


    @PutMapping("edit/room")
    public void EditRoom(RoomEntity roomEntity){
        return;

    }

    @DeleteMapping("/deleteRoom/{roomId}")
    public HashMap<String,String> deleteRoom( @PathVariable Long roomId){
        return iRoomService.deleteRoomById(roomId);

    }


}
