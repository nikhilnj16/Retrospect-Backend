package com.backend.Retrospect.user.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLoginDTO {

    String userEmail;
    String userPassword;
}
