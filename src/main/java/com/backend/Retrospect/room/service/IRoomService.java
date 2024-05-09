package com.backend.Retrospect.room.service;


import com.backend.Retrospect.room.entity.RoomEntity;

import java.util.List;

public interface IRoomService {
    List<com.backend.Retrospect.room.entity.RoomEntity> getAllRooms();
    String createRoom(RoomEntity roomEntity);
    com.backend.Retrospect.room.entity.RoomEntity getRoomById(Long id);
    RoomEntity  editRoomById(Long id,RoomEntity roomEntity);
    void removeRoom(Long id);

}