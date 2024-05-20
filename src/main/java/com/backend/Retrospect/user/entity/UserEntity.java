package com.backend.Retrospect.user.entity;

import com.backend.Retrospect.room.entity.RoomEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull(message = "userName should not be null")
    @Size(message = "Enter userName between 3 to 100 chars")
    private String userName;

    @NotNull(message = "userEmail should not be null")
    @Email(message = "Enter email in correct format")
    private String userEmail;

    @NotNull(message = "userPassword should not be null")
    private String userPassword;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<RoomEntity> createdRooms = new ArrayList<>();
}


