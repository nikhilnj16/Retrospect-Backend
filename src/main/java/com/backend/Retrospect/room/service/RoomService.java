package com.backend.Retrospect.room.service;


import com.backend.Retrospect.room.entity.RoomEntity;
import com.backend.Retrospect.room.repository.IRoomRepository;
import com.backend.Retrospect.user.utility.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService implements IRoomService {

    @Autowired
    private IRoomRepository repoRoom;
    @Autowired
    private EmailSender emailSender;




    @Override
    public List<com.backend.Retrospect.room.entity.RoomEntity> getAllRooms() {

        return repoRoom.findAll();
    }

    @Override
    public String createRoom(RoomEntity roomEntity) {

        List<String> topicList= roomEntity.getTopicList();
        repoRoom.save(roomEntity);
        System.out.println(topicList);
        
        if (topicList.isEmpty()) {
            throw new IllegalArgumentException("At least one topiclist is required");
        }
        return "Room Created Successfully ";
    }

    @Override
    public com.backend.Retrospect.room.entity.RoomEntity getRoomById(Long id) {

         return repoRoom.findById(id).get();
    }

    @Override
    public RoomEntity editRoomById(Long id,RoomEntity roomEntity) {

  roomEntity.setRoomName("");
  roomEntity.setRoomDescription("");
  roomEntity.setRoomCreator("");



  return repoRoom.save(roomEntity);

    }

    @Override
    public void removeRoom(Long id) {

     repoRoom.deleteById(id);


    }
}