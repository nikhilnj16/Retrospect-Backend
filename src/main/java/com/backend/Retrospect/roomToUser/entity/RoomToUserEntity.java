package com.backend.Retrospect.roomToUser.entity;

import com.backend.Retrospect.room.entity.RoomEntity;
import com.backend.Retrospect.user.entity.UserEntity;
import com.backend.Retrospect.user.utility.UserToken;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="room-to-user")
public class RoomToUserEntity {

    @EmbeddedId
    private RoomToUserId id;


}
