package com.backend.Retrospect.user.service;

import com.backend.Retrospect.user.DTO.UserDetailsChangeDTO;
import com.backend.Retrospect.user.DTO.UserEmailDTO;
import com.backend.Retrospect.user.DTO.UserLoginDTO;
import com.backend.Retrospect.user.DTO.UserPasswordChangeDTO;
import com.backend.Retrospect.user.entity.UserEntity;
import com.backend.Retrospect.user.repository.IUserRepository;
import com.backend.Retrospect.user.utility.EmailSender;
import com.backend.Retrospect.user.utility.UserToken;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserRepository repository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserToken userToken;

    @Autowired
    EmailSender emailSender;

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
        HashMap<String, String> response = new HashMap<>();
        if(userEntity != null && passwordEncoder.matches(userLoginDto.getUserPassword(), userEntity.getUserPassword())) {
            String token = userToken.createToken(userEntity.getUserName());
            String userEmail = userEntity.getUserEmail();
            repository.save(userEntity);

            response.put("Status" , "OK");
            response.put("token", token);
            response.put("userEmail",userEmail);
            response.put("userName",userEntity.getUserName());
            return response;

        } else {
            response.put("Status" , "Login failed");
            return response;
        }

    }

    @Override
    public UserEntity getUser(String token) {
        String userName = userToken.decodeToken(token);
        return repository.findByName(userName);
    }

    @Override
    public HashMap<String, String> changeEmail(UserDetailsChangeDTO userDetailsChangeDTO, String token) {
        String userName = userToken.decodeToken(token);
        UserEntity userEntity = repository.findByName(userName);
        if(userEntity != null){
            userEntity.setUserEmail(userDetailsChangeDTO.getUserEmail());
            repository.save(userEntity);
            HashMap<String, String> response = new HashMap<>();
            response.put("Status" , "OK");
            return response;
        } else {
            HashMap<String, String> response = new HashMap<>();
            response.put("Status" , "Not OK");
            return response;
        }

    }

    @Override
    public HashMap<String, String> changePassword(UserPasswordChangeDTO userPasswordChangeDTO, String token) {
        HashMap<String, String> response = new HashMap<>();
        String userName = userToken.decodeToken(token);
        UserEntity userEntity = repository.findByName(userName);
        if(userEntity == null){
            response.put("Status" , "User Not found");
        } else {
            if(passwordEncoder.matches(userPasswordChangeDTO.getOldPassword(), userEntity.getUserPassword())){
                String userNewEncodedPassword = passwordEncoder.encode(userPasswordChangeDTO.getNewPassword());
                userEntity.setUserPassword(userNewEncodedPassword);
                repository.save(userEntity);
                response.put("Status" , "OK");
            } else {
                response.put("Status" , "Old password entered is wrong");
            }
        }
        return response;

    }


    @Override
    public ResponseEntity<List<String>> getAllEmailId() {

        List<String> emails=repository.findALLEmail();
        return ResponseEntity.ok(emails);


    }


    public void sendEmailToAllUsers(UserEmailDTO userEmailDTO, String link)
    {
        UserEntity userEntity = repository.findByEmail(userEmailDTO.getUserEmail());

        if(userEntity==null)
        {
            System.out.println("Email id is not present");
        }
        String subject="Report is added";
        String body="The mail is sent to"+link;
        emailSender.sendEmail(userEmailDTO.getUserEmail(),subject,body);

    }


    public boolean authenticate(String oldpassword, String password) {

        return passwordEncoder.matches(password, oldpassword);

    }








}
