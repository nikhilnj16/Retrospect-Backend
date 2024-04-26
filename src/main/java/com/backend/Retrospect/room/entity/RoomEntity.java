package com.backend.Retrospect.room.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@ToString
@Entity
@Table(name="room")
public class RoomEntity {
    @Id
    @GeneratedValue
    public Integer roomId;
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

    public RoomEntity(Integer roomId, String roomName, String roomCreator, Date startDate, Date endDate, boolean active, String roomDescription) {
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

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomCreator() {
        return roomCreator;
    }

    public void setRoomCreator(String roomCreator) {
        this.roomCreator = roomCreator;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        active = active;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }
}
