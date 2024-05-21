package com.backend.Retrospect.topic.entity;

import com.backend.Retrospect.room.entity.RoomEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "topic")
@Data
public class TopicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long topicId;
    String topicName;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonIgnore
    private RoomEntity room;
}
