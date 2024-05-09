package com.backend.Retrospect.roomToUser.dto;


import lombok.Data;

@Data
public class UsersInRoom {
    private Long userId;
    private String userName;
    private String userEmail;
}
