package com.backend.Retrospect.topic.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "topic")
public class TopicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long topicId;
    String topicName;

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public TopicEntity(long topicId, String topicName) {
        this.topicId = topicId;
        this.topicName = topicName;
    }
    public TopicEntity(){

    }
}
