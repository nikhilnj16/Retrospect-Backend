package com.backend.Retrospect.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.text.DateFormat;

@Entity
@Data
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer userId;
    @NotNull(message = "userName should not be null")
    @Size(message = "Enter userName between 3 to 100 chars")
    String userName;
    @NotNull(message = "userEmail should not be null")
    @Email(message = "Enter email in correct format")
    String userEmail;
    @NotNull(message = "userPassword should not be null")
    String userPassword;
    Integer userPhoneNo;


}
