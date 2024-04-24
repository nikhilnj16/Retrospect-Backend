package com.backend.Retrospect.room.service;

import com.backend.Retrospect.room.entity.RoomEntity;
import com.backend.Retrospect.room.repository.IRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService implements IRoomService {
    @Autowired
    private IRoomRepository roomrepo;

    @Override
    public List<RoomEntity> getAllRooms() {
        return roomrepo.findAll();
    }
    @Override
    public List<RoomEntity> createroom(RoomEntity roomEntity) {
        roomrepo.save(roomEntity);
        return getAllRooms();
    }
}
