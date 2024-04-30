package com.backend.Retrospect.room.service;


import com.backend.Retrospect.room.entiry.RoomEntity;

import java.util.HashMap;
import java.util.List;

public interface IRoomService {
    List<RoomEntity> getAllRooms();
    HashMap<String ,String > createRoom(RoomEntity roomEntity);
    String editRoomById(Long id,RoomEntity roomEntity);
    HashMap<String ,String> deleteRoomById(Long id);
}