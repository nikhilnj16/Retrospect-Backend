package com.backend.Retrospect.room.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="room")
public class RoomEntity {
    @Id
    @GeneratedValue
    public Long roomId;
    @NotNull(message = "userName should not be null")
//    @Size(message = "Enter userName between 3 to 100 chars")
    private String roomName;
    @NotNull(message = "roomCreator should not be null")
    private String roomCreator;

    private boolean active;
//    @Size(message = "Enter notes between 3 to 100 chars")
    private String roomDescription;

}