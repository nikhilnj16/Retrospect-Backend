package com.backend.Retrospect.room.service;


import com.backend.Retrospect.room.entiry.RoomEntity;

import java.util.List;

public interface IRoomService {
    List<RoomEntity> getAllRooms();
    String createRoom(RoomEntity roomEntity);
    RoomEntity getRoomById(Long id);
    RoomEntity  editRoomById(Long id,RoomEntity roomEntity);
    void removeRoom(Long id);

}