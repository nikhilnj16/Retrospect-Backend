package com.backend.Retrospect.room.service;


import com.backend.Retrospect.room.entiry.RoomEntity;
import com.backend.Retrospect.room.repository.IRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    public HashMap<String,String> createRoom(RoomEntity roomEntity) {
        repoRoom.save(roomEntity);
        HashMap<String,String> map = new HashMap<>();
        map.put("status","successfully crested room");
        return map;
    }

    @Override
    public String editRoomById(Long id, RoomEntity roomEntity) {
        return null;

    }

    @Override
    public HashMap<String ,String> deleteRoomById(Long id) {
        repoRoom.deleteById(id);
        HashMap<String ,String> map = new HashMap<>();
        map.put("id",String.valueOf(id));
        map.put("status","deleted");
        return map;
    }
}