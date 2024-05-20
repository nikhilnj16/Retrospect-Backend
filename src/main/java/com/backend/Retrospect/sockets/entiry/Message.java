package com.backend.Retrospect.sockets.entiry;

import com.backend.Retrospect.roomToUser.entity.RoomToUserEntity;
import com.backend.Retrospect.sockets.entiry.BaseModel;
import com.backend.Retrospect.sockets.entiry.MessageType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Message extends BaseModel {

    @Enumerated(EnumType.STRING)
    private MessageType messageType;
    private String content;
    private String room;
    private String username;
    private String contentType;

    @ManyToOne
    private RoomToUserEntity roomToUser;
}