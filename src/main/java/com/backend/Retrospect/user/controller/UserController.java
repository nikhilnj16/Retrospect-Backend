package com.backend.Retrospect.user.controller;

import com.backend.Retrospect.user.DTO.UserLoginDTO;
import com.backend.Retrospect.user.entity.UserEntity;
import com.backend.Retrospect.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserServiceImpl service;

    @PostMapping("/register")
    public HashMap<String, String> userRegistration(@RequestBody UserEntity userEntity){
        return service.userRegistration(userEntity);
    }
    @PostMapping("/login")
    public HashMap<String, String> userLogin(@RequestBody UserLoginDTO userLoginDto){
        return service.userLogin(userLoginDto);
    }

    @GetMapping("/getUserByToken")
    public UserEntity getUser(@RequestHeader String token){
        return service.getUser(token);
    }




}
