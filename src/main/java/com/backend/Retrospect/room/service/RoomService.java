package com.backend.Retrospect.room.service;


import com.backend.Retrospect.room.entity.RoomEntity;
import com.backend.Retrospect.room.repository.IRoomRepository;
import com.backend.Retrospect.user.entity.UserEntity;
import com.backend.Retrospect.user.repository.IUserRepository;
import com.backend.Retrospect.user.utility.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.HashMap;
import java.util.List;

@Service
public class RoomService implements IRoomService {

    @Autowired
    private IRoomRepository repoRoom;

    @Autowired
    private IUserRepository repoUser;

    @Autowired
    private UserToken userToken;


    @Override
    public List<RoomEntity> getAllRooms(@RequestHeader String token) {
        String username = userToken.decodeToken(token);
        UserEntity userEntity = repoUser.findByName(username);
        if(userEntity != null){
            return repoRoom.findAll();
        }
        else{
            return null;
        }

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