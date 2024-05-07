package com.backend.Retrospect.roomToUser.entity;

import jakarta.persistence.Embeddable;
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

    private Long roomId;

    private Long userId;

    private String timeStamp;
}
