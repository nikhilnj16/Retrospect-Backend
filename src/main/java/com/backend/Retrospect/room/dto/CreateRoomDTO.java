package com.backend.Retrospect.room.dto;

import lombok.*;

@Data
public class CreateRoomDTO {

    private String roomName;
    private String user;
    private boolean active;
    private String roomDescription;
    private boolean restrictedRoom;
    private String restrictedRoomPassKey;
}
