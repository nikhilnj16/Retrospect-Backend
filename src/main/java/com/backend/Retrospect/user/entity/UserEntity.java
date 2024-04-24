package com.backend.Retrospect.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.text.DateFormat;

@Entity

@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;
    @NotNull(message = "userName should not be null")
    @Size(message = "Enter userName between 3 to 100 chars")
    String userName;
    @NotNull(message = "userEmail should not be null")
    @Email(message = "Enter email in correct format")
    String userEmail;
    @NotNull(message = "userPassword should not be null")
    String userPassword;
    Integer userPhoneNo;

    public Long getUserId() {
        return userId;
    }
    public UserEntity() {

    }

    public UserEntity(Long userId, String userPassword, Integer userPhoneNo, String userName, String userEmail) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userPhoneNo = userPhoneNo;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public Integer getUserPhoneNo() {
        return userPhoneNo;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserPhoneNo(Integer userPhoneNo) {
        this.userPhoneNo = userPhoneNo;
    }
}
