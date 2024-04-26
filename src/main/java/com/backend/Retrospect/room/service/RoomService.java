package com.backend.Retrospect.room.service;


import com.backend.Retrospect.room.entiry.RoomEntity;
import com.backend.Retrospect.room.repository.IRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService implements IRoomService {

    @Autowired
    private IRoomRepository repoRoom;


    @Override
    public List<RoomEntity> getAllRooms() {
        return repoRoom.findAll();
    }

    @Override
    public List<RoomEntity> createRoom(RoomEntity roomEntity) {
        repoRoom.save(roomEntity);
        return repoRoom.findAll();
    }

    @Override
    public RoomEntity getRoomById(Long id) {
        return null;
    }

    @Override
    public String editRoomById(Long id, RoomEntity roomEntity) {
        return "";
    }

    @Override
    public String deleteRoomById(Long id) {
        return "";
    }
}