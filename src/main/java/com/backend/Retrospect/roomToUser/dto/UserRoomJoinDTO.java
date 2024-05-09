package com.backend.Retrospect.roomToUser.dto;

import lombok.Data;

@Data
public class UserRoomJoinDTO {

    private long userId;
    private long roomId;
    private String timeStamp;
}
