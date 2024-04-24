package com.backend.Retrospect.topic.repository;

import com.backend.Retrospect.topic.entity.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITopicRepository extends JpaRepository<TopicEntity, Long> {
}
