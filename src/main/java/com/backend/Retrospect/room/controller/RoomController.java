package com.backend.Retrospect.room.controller;


import com.backend.Retrospect.room.entiry.RoomEntity;
import com.backend.Retrospect.room.service.IRoomService;
import org.springdoc.core.configuration.SpringDocUIConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> roomCreation(@RequestBody RoomEntity roomEntity) {
        String registrationMessage = iRoomService.createRoom(roomEntity);
        Map<String, String> response = new HashMap<>();
        response.put("message", registrationMessage);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public void  removeRoom(@PathVariable long id)
    {
        iRoomService.removeRoom(id);

    }
    @PutMapping("/update")
    public  RoomEntity updateRoom(@PathVariable long id,@RequestBody RoomEntity roomEntity)
    {
       return iRoomService.editRoomById(id,roomEntity);
    }




}
