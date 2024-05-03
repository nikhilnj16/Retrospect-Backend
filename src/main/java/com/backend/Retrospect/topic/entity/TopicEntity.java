package com.backend.Retrospect.topic.entity;

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
}
