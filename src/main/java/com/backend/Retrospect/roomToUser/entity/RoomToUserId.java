package com.backend.Retrospect.roomToUser.entity;

import com.backend.Retrospect.room.entity.RoomEntity;
import com.backend.Retrospect.user.entity.UserEntity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class RoomToUserId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "roomId")
    private RoomEntity roomEntity;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity userEntity;

    private String timeStamp;


}
