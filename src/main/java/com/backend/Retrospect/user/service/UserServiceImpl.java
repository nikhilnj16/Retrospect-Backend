package com.backend.Retrospect.user.service;

import com.backend.Retrospect.user.DTO.UserLoginDTO;
import com.backend.Retrospect.user.entity.UserEntity;
import com.backend.Retrospect.user.repository.IUserRepository;
import com.backend.Retrospect.user.utility.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserRepository repository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserToken userToken;

    @Override
    public HashMap<String, String> userRegistration(UserEntity userEntity) {

        String encodePassword = passwordEncoder.encode(userEntity.getUserPassword());
        userEntity.setUserPassword(encodePassword);
        repository.save(userEntity);

        HashMap<String, String> response = new HashMap<>();
        response.put("Status" , "OK");
        return response;
    }

    @Override
    public HashMap<String, String> userLogin(UserLoginDTO userLoginDto) {
        UserEntity userEntity = repository.findByEmail(userLoginDto.getUserEmail());
        if(userEntity != null && passwordEncoder.matches(userLoginDto.getUserPassword(), userEntity.getUserPassword())) {
            String token = userToken.createToken(userEntity.getUserName());
            String userEmail = userEntity.getUserEmail();
            repository.save(userEntity);
            HashMap<String, String> response = new HashMap<>();
            response.put("Status" , "OK");
            response.put("token", token);
            response.put("userEmail",userEmail);
            response.put("userName",userEntity.getUserName());
            return response;

        } else {
            return null;
        }

    }

    @Override
    public UserEntity getUser(String token) {
        String userName = userToken.decodeToken(token);
        return repository.findByName(userName);
    }
}
