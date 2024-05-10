package com.backend.Retrospect.roomToUser.service;

import com.backend.Retrospect.room.entity.RoomEntity;
import com.backend.Retrospect.room.repository.IRoomRepository;
import com.backend.Retrospect.roomToUser.dto.UserRoomJoinDTO;
import com.backend.Retrospect.roomToUser.entity.RoomToUserEntity;
import com.backend.Retrospect.roomToUser.entity.RoomToUserId;
import com.backend.Retrospect.roomToUser.repository.IRoomToUserRepository;
import com.backend.Retrospect.user.entity.UserEntity;
import com.backend.Retrospect.user.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class RoomToUserService implements IRoomToUserService{

    @Autowired
    IRoomToUserRepository iRoomToUserRepository;

    @Autowired
    IRoomRepository roomRepository;

    @Autowired
    IUserRepository userRepository;


    @Override
    public HashMap<String, String> UserJoinedRoom(UserRoomJoinDTO userRoomJoinDTO) {
        Optional<RoomEntity> roomOptional = roomRepository.findById(Long.valueOf(userRoomJoinDTO.getRoomId()));
        Optional<UserEntity> userOptional = userRepository.findById(Long.valueOf(userRoomJoinDTO.getUserId()));
        HashMap<String, String> map = new HashMap<>();

        if (roomOptional.isPresent() && userOptional.isPresent()) {
            RoomEntity roomEntity = roomOptional.get();
            UserEntity userEntity = userOptional.get();

            RoomToUserId roomToUserId = new RoomToUserId();
            roomToUserId.setRoomEntity(roomEntity);
            roomToUserId.setUserEntity(userEntity);

            LocalDateTime timeStamp = LocalDateTime.now();
            roomToUserId.setTimeStamp(timeStamp.toString());

            try {
                RoomToUserEntity roomToUserEntity = new RoomToUserEntity();
                roomToUserEntity.setId(roomToUserId);
                iRoomToUserRepository.save(roomToUserEntity);
                map.put("status", "success");
            } catch (Exception e) {
                e.printStackTrace();
                map.put("status", "error :" + e.getMessage());
            }
        } else {
            map.put("status", "error: Room or User not found");
        }
        return map;
    }

    @Override
    public List<RoomToUserEntity> usersInRoom(String roomId) {
          return iRoomToUserRepository.findAllByIdRoomEntityRoomId(Long.valueOf(roomId));
    }

}
