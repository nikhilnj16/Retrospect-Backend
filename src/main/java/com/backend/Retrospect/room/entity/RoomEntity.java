package com.backend.Retrospect.room.entity;


import com.backend.Retrospect.topic.entity.TopicEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


@ToString
@Entity
@Getter
@Setter

@Table(name="room")
public class RoomEntity {
    @Id
    @GeneratedValue
    public Long roomId;
    @NotNull(message = "userName should not be null")
    @Size(message = "Enter userName between 3 to 100 chars")
    private String roomName;
    @NotNull(message = "roomCreator should not be null")
    private String roomCreator;
    @DateTimeFormat(pattern = "dd/mm/yyyy")
     private Date startDate;
    @DateTimeFormat(pattern = "dd/mm/yyyy")
    private Date endDate;
    private boolean active;

    @Size(message = "Enter notes between 3 to 100 chars")
    String roomDescription;

    List<String> topicList;
    public RoomEntity(Long roomId, String roomName, String roomCreator, Date startDate, Date endDate, boolean active, String roomDescription) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomCreator = roomCreator;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = active;
        this.roomDescription = roomDescription;
    }

    public RoomEntity(){

    }

}
