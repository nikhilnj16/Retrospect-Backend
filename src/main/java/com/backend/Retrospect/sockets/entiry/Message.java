package com.backend.Retrospect.sockets.entiry;

import com.backend.Retrospect.roomToUser.entity.RoomToUserEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message extends BaseModel {

    @Enumerated(EnumType.STRING)
    private MessageType messageType;
    private String content;
    private String contentType;
    private String room;
    private String username;
    private Integer likes;
    private String likedBy;
    @ManyToOne
    private RoomToUserEntity roomToUser;
}
