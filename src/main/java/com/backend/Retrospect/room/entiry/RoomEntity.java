package com.backend.Retrospect.room.entiry;


import com.backend.Retrospect.topic.entity.TopicEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
    private String roomDescription;


    @ElementCollection
    private List<String> topicList;








}