package com.backend.Retrospect.roomToUser.service;

import com.backend.Retrospect.roomToUser.dto.UserRoomJoinDTO;
import com.backend.Retrospect.roomToUser.entity.RoomToUserId;

import java.util.HashMap;

public interface IRoomToUserService {

    HashMap<String, String> UserJoinedRoom(UserRoomJoinDTO userRoomJoinDTO);

}
