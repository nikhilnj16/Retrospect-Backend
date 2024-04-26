package com.backend.Retrospect.room.service;


import com.backend.Retrospect.room.entiry.RoomEntity;

import java.util.List;

public interface IRoomService {
    List<RoomEntity> getAllRooms();
    List<RoomEntity> createRoom(RoomEntity roomEntity);
    RoomEntity getRoomById(Long id);
    String editRoomById(Long id,RoomEntity roomEntity);
    String deleteRoomById(Long id);
}