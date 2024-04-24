package com.backend.Retrospect.room.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity

public class RoomEntity {

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
}
