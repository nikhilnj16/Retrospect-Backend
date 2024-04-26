package com.backend.Retrospect.room.controller;

import com.backend.Retrospect.room.entity.RoomEntity;

import com.backend.Retrospect.room.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/getAllRooms")
    public List<RoomEntity> getAllRooms(){
        return roomService.getAllRooms();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public List<RoomEntity> createRoom(@RequestBody RoomEntity roomEntity){
        System.out.println(roomEntity);
        return roomService.createroom(roomEntity);
    }

    @GetMapping("/getRoomById/{id}")
    public RoomEntity getRoomById(@PathVariable int id){
        return roomService.getRoomById(id);
    }

    @PutMapping("edit/{id}")
    public String editRoomById (@PathVariable int id, RoomEntity roomEntity)
    {
        return roomService.editRoomById(id,roomEntity);
    }

    @DeleteMapping("/delete")
    public String deleteRoomById(@PathVariable int id)
    {
        return roomService.deleteRoomById(id);
    }

}
