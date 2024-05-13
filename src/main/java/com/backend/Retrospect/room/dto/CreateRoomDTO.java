package com.backend.Retrospect.room.dto;

import lombok.Data;

@Data
public class CreateRoomDTO {
    private String roomName;
    private String user;
    private boolean active;
    private String roomDescription;
}
