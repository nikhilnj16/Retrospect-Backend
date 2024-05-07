package com.backend.Retrospect.roomToUser.repository;

import com.backend.Retrospect.roomToUser.entity.RoomToUserEntity;
import com.backend.Retrospect.roomToUser.entity.RoomToUserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoomToUserRepository extends JpaRepository<RoomToUserEntity, RoomToUserId> {
}
