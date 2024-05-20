package com.backend.Retrospect.user.service;


import com.backend.Retrospect.user.DTO.UserDetailsChangeDTO;
import com.backend.Retrospect.user.DTO.UserLoginDTO;
import com.backend.Retrospect.user.DTO.UserPasswordChangeDTO;
import com.backend.Retrospect.user.entity.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface IUserService extends UserDetailsService {
    HashMap<String, String> userRegistration(UserEntity userEntity);

    HashMap<String, String> userLogin(UserLoginDTO userLoginDto);

    UserEntity getUser(String token);

    Optional<UserEntity> getUser(long id);

    HashMap<String, String> changeEmail(UserDetailsChangeDTO userDetailsChangeDTO, String token);

    HashMap<String, String> changePassword(UserPasswordChangeDTO userPasswordChangeDTO, String token);

    ResponseEntity<List<String>> getAllEmailId();

   UserEntity getUserDetails(String email);
}
