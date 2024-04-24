package com.backend.Retrospect.room.service;

import com.backend.Retrospect.room.entity.RoomEntity;

import java.util.List;

public interface IRoomService {
    List<RoomEntity> getAllRooms();
    List<RoomEntity> createroom(RoomEntity roomEntity);
    RoomEntity getRoomById(int id);
    String editRoomById(int id,RoomEntity roomEntity);
    String deleteRoomById(int id);
}
