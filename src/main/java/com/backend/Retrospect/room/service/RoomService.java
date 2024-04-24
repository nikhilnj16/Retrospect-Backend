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

    @Override
    public RoomEntity getRoomById(int id) {
        return roomrepo.findById(id).orElseThrow(()->new IllegalArgumentException("Room Not Found"));
    }

    @Override
    public String editRoomById(int id,RoomEntity roomEntity) {
        RoomEntity updateDetails = roomrepo.findById(id).orElseThrow(()->new IllegalArgumentException("Room Not Found"));
        updateDetails.setRoomName(roomEntity.getRoomName());
        updateDetails.setRoomCreator(roomEntity.getRoomCreator());
        updateDetails.setRoomDescription(roomEntity.getRoomDescription());
        updateDetails.setActive(roomEntity.getActive());
        updateDetails.setStartDate(roomEntity.getStartDate());
        updateDetails.setEndDate(roomEntity.getEndDate());
        roomrepo.save(updateDetails);
        return "Room Detail Updated";
    }

    public String deleteRoomById(int id) {
        if(roomrepo.existsById(id))
        {
            roomrepo.deleteById(id);
            return "Room Deleted .";
        }

        else
            return "Room Not Found with :" + id;
    }


}
