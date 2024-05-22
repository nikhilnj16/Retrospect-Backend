package com.backend.Retrospect.room.service;


import com.backend.Retrospect.room.dto.CreateRoomDTO;
import com.backend.Retrospect.room.dto.RoomPassKeyDTO;
import com.backend.Retrospect.room.entity.RoomEntity;
import com.backend.Retrospect.room.repository.IRoomRepository;
import com.backend.Retrospect.roomToUser.repository.IRoomToUserRepository;
import com.backend.Retrospect.topic.dto.TopicDTO;
import com.backend.Retrospect.topic.entity.TopicEntity;
import com.backend.Retrospect.topic.repository.ITopicRepository;
import com.backend.Retrospect.user.entity.UserEntity;
import com.backend.Retrospect.user.repository.IUserRepository;
import com.backend.Retrospect.user.utility.EmailSender;
import com.backend.Retrospect.user.utility.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService implements IRoomService {

    @Autowired
    private IRoomRepository repoRoom;
    @Autowired
    private IUserRepository repoUser;

    @Autowired
    private UserToken userToken;

    @Autowired
    IRoomToUserRepository roomToUserRepository;

    @Autowired
    ITopicRepository topicRepository;


    @Override
    public List<RoomEntity> getAllRooms(@RequestHeader String token) {
        String username = userToken.decodeToken(token);
        UserEntity userEntity = repoUser.findByName(username);
        if(userEntity != null){
            return repoRoom.findAll();
        }
        else{
            return null;
        }

    }




    @Override
    public String editRoomById(Long id, RoomEntity roomEntity) {
        return null;

    }

    @Override
    public HashMap<String ,String> deleteRoomById(Long id) {
        repoRoom.deleteById(id);
        HashMap<String ,String> map = new HashMap<>();
        map.put("id",String.valueOf(id));
        map.put("status","deleted");
        return map;
    }

    @Override
    public HashMap<String, String> create(CreateRoomDTO createRoomDTO) {
        RoomEntity roomEntity = new RoomEntity();

        // Retrieve the UserEntity from the Optional
        Optional<UserEntity> userEntityOptional = repoUser.findById(Long.valueOf(createRoomDTO.getUser()));
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();

            // Set room name, description, and active status from the DTO
            roomEntity.setRoomName(createRoomDTO.getRoomName());
            roomEntity.setRoomDescription(createRoomDTO.getRoomDescription());
            roomEntity.setActive(createRoomDTO.isActive());
            roomEntity.setRestrictedRoom(createRoomDTO.isRestrictedRoom());
            roomEntity.setRestrictedRoomPassKey(createRoomDTO.getRestrictedRoomPassKey());
            // Set room active status (you can uncomment this line if you have a way to determine the active status)
            // roomEntity.setActive(createRoomDTO.isActive());

            // Set the user entity to the room entity
            roomEntity.setUser(userEntity);

            // Map topic IDs to TopicEntity objects
            List<TopicEntity> topics = new ArrayList<>();
            for (TopicDTO topicDTO : createRoomDTO.getTopics()) {
                Optional<TopicEntity> topicEntityOptional = topicRepository.findById(topicDTO.getTopicId());
                if (topicEntityOptional.isPresent()) {
                    TopicEntity topicEntity = topicEntityOptional.get();
                    topics.add(topicEntity);
                } else {
                    // Handle the case where a topic with the specified ID is not found
                    HashMap<String, String> map = new HashMap<>();
                    map.put("status", "failed to create room: topic not found with ID " + topicDTO.getTopicId());
                    return map;
                }
            }
            roomEntity.setTopics(topics);

            // Save the room entity
            repoRoom.save(roomEntity);

            // Return a success message
            HashMap<String,String> map = new HashMap<>();
            map.put("status", "successfully created");
            return map;
        } else {
            // Handle the case where the user with the specified ID is not found
            HashMap<String,String> map = new HashMap<>();
            map.put("status", "failed to create room: user not found");
            return map;
        }
    }
    @Override
    public HashMap<String, String> roomPassKeyChecker(RoomPassKeyDTO roomPassKeyDTO) {
        HashMap<String, String> result = new HashMap<>();
        Optional<RoomEntity> roomEntityOptional = repoRoom.findById(roomPassKeyDTO.getRoomId());
        if (roomEntityOptional.isPresent()) {
            RoomEntity roomEntity = roomEntityOptional.get();
            if (roomPassKeyDTO.getRoomPassKey().equals(roomEntity.getRestrictedRoomPassKey())) {
                result.put("status", "success");
                result.put("message", "Passkey is correct.");
            } else {
                result.put("status", "failure");
                result.put("message", "Passkey is incorrect.");
            }
        } else {
            result.put("status", "failure");
            result.put("message", "Room not found.");
        }
        return result;
    }

    @Override
    public Optional<RoomEntity> getRoom(String id) {
        return repoRoom.findById(Long.valueOf(id));
    }


}