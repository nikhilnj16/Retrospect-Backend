package com.backend.Retrospect.room.dto;

import com.backend.Retrospect.user.entity.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class CreateRoomDTO {

    private String roomName;
    private String user;
    private boolean active;
    private String roomDescription;
}
