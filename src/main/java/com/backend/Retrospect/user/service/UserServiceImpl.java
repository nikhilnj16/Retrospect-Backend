package com.backend.Retrospect.user.service;

import com.backend.Retrospect.user.DTO.UserLoginDTO;
import com.backend.Retrospect.user.entity.UserEntity;
import com.backend.Retrospect.user.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository repository;


    @Override
    public HashMap<String, String> userRegistration(UserEntity userEntity) {

    }

    @Override
    public HashMap<String, String> userLogin(UserLoginDTO userLoginDto) {
        return null;
    }

    @Override
    public UserEntity getUser(String token) {
        return null;
    }
}
