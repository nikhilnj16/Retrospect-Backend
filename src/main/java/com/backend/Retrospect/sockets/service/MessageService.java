package com.backend.Retrospect.sockets.service;

import com.backend.Retrospect.sockets.entiry.Message;
import com.backend.Retrospect.sockets.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public HashMap<String, Integer> analysisMessages(String room) {
        HashMap<String, Integer> analysis = new HashMap<>();
        List<Message> messageList = messageRepository.findAllByRoom(room);

        for (Message message : messageList) {
            String contentType = message.getContentType();

            // Check if the content type is already present in the analysis map
            if (analysis.containsKey(contentType)) {
                // If it is, increment the count by 1
                analysis.put(contentType, analysis.get(contentType) + 1);
            } else {
                // If it's not present, add it to the map with count as 1
                analysis.put(contentType, 1);
            }
        }

        return analysis;
    }

}