package com.backend.Retrospect.room.dto;

import com.backend.Retrospect.topic.dto.TopicDTO;
import lombok.*;

import java.util.List;

@Data
public class CreateRoomDTO {
    private String roomName;
    private String user;
    private boolean active;
    private String roomDescription;
    private boolean restrictedRoom;
    private String restrictedRoomPassKey;
    private List<TopicDTO> topics; // Use TopicDTO instead of TopicEntity
}
