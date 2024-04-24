package com.backend.Retrospect.room.entity;

import com.backend.Retrospect.room.controller.RoomController;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@ToString

@Entity
@Table(name="room")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer roomId;
    @NotNull(message = "userName should not be null")
    @Size(message = "Enter userName between 3 to 100 chars")
    String roomName;
    @NotNull(message = "roomCreator should not be null")
    String roomCreator;
    @DateTimeFormat(pattern = "dd/mm/yyyy")
    LocalDate startDate;
    @DateTimeFormat(pattern = "dd/mm/yyyy")
    LocalDate endDate;

    Boolean Active;
    @Size(message = "Enter notes between 3 to 100 chars")
    String roomDescription;

    public RoomEntity(Integer roomId, String roomName, String roomCreator, LocalDate startDate, LocalDate endDate, Boolean active, String roomDescription) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomCreator = roomCreator;
        this.startDate = startDate;
        this.endDate = endDate;
        Active = active;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getActive() {
        return Active;
    }

    public void setActive(Boolean active) {
        Active = active;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }
}
