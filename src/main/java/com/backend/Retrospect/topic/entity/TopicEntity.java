package com.backend.Retrospect.topic.entity;

import com.backend.Retrospect.room.entity.RoomEntity;
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

    public TopicEntity(long topicId, String topicName) {
        this.topicId = topicId;
        this.topicName = topicName;
    }
    public TopicEntity(){

    }
}
