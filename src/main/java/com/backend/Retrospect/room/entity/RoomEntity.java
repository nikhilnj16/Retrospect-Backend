package com.backend.Retrospect.room.entity;


import com.backend.Retrospect.topic.entity.TopicEntity;
import com.backend.Retrospect.user.entity.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @NotNull(message = "roomName should not be null")
    private String roomName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private boolean active;

    private String roomDescription;

    private boolean restrictedRoom;

    private String restrictedRoomPassKey;

    @ManyToMany
    @JoinTable(
            name = "room_topic",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private List<TopicEntity> topics;
}

