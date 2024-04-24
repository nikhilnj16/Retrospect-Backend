package com.backend.Retrospect.user.service;


import com.backend.Retrospect.user.DTO.UserLoginDTO;
import com.backend.Retrospect.user.entity.UserEntity;

import java.util.HashMap;

public interface IUserService {
    HashMap<String, String> userRegistration(UserEntity userEntity);

    HashMap<String, String> userLogin(UserLoginDTO userLoginDto);

    UserEntity getUser(String token);

}
