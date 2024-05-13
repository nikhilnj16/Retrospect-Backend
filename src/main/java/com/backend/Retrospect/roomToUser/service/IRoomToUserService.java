package com.backend.Retrospect.roomToUser.service;

import com.backend.Retrospect.roomToUser.dto.UserRoomJoinDTO;
import com.backend.Retrospect.roomToUser.entity.RoomToUserEntity;

import java.util.HashMap;
import java.util.List;

public interface IRoomToUserService {
    HashMap<String, String> UserJoinedRoom(UserRoomJoinDTO userRoomJoinDTO);

    List<RoomToUserEntity> usersInRoom(String roomId);
}
