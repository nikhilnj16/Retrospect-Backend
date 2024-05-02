package com.backend.Retrospect.room.service;


import com.backend.Retrospect.room.entity.RoomEntity;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.HashMap;
import java.util.List;

public interface IRoomService {
    List<RoomEntity> getAllRooms(@RequestHeader String token);
    HashMap<String ,String > createRoom(RoomEntity roomEntity);
    String editRoomById(Long id,RoomEntity roomEntity);
    HashMap<String ,String> deleteRoomById(Long id);
}