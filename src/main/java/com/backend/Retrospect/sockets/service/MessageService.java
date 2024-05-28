package com.backend.Retrospect.sockets.service;

import com.backend.Retrospect.sockets.entiry.Message;
import com.backend.Retrospect.sockets.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageService {
    @Autowired
    MessageRepository messageRepository;


    public List<Message> getMessages(String room) {
        return messageRepository.findAllByRoom(room);
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public HashMap<String, String> deleteMessageById(Long messageId) {
        HashMap<String, String> message = new HashMap<>();
        messageRepository.deleteById(messageId);
        message.put("status", "success");
        return message;

    }

    public Integer likeMessage(Long messageId, String user) {
        Message message= messageRepository.findById(messageId).get();
        Integer count = message.getLikes();
        // Initialize likedBy if it is null
        String likedByStr = message.getLikedBy();
        List<String> likedBy = new ArrayList<>();
        if (likedByStr != null && !likedByStr.isEmpty()) {
            likedBy = new ArrayList<>(Arrays.asList(likedByStr.split(",")));
        }
        if (count == null) {
            count = 0;
        }
        if (likedBy.contains(user)) {
            likedBy.remove(user);
            count--;
        } else {
            likedBy.add(user);
            count++;
        }
        message.setLikes(count);
        message.setLikedBy(StringUtils.join(likedBy, ","));
        // Save the message and return the new like count
        messageRepository.save(message);
        return message.getLikes();
    }

//    public HashMap<String, Integer> analysisMessages(String room) {
//        HashMap<String, Integer> analysis = new HashMap<>();
//        List<Message> messageList = messageRepository.findAllByRoom(room);
//
//        for (Message message : messageList) {
//            String contentType = message.getContentType();
//
//            // Check if the content type is already present in the analysis map
//            if (analysis.containsKey(contentType)) {
//                // If it is, increment the count by 1
//                analysis.put(contentType, analysis.get(contentType) + 1);
//            } else {
//                // If it's not present, add it to the map with count as 1
//                analysis.put(contentType, 1);
//            }
//        }
//
//        return analysis;
//    }

}