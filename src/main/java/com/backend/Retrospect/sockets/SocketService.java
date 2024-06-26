package com.backend.Retrospect.sockets;

import com.backend.Retrospect.roomToUser.entity.RoomToUserEntity;
import com.backend.Retrospect.roomToUser.entity.RoomToUserId;
import com.backend.Retrospect.sockets.entiry.Message;
import com.backend.Retrospect.sockets.service.MessageService;
import com.corundumstudio.socketio.SocketIOClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SocketService {


    private final MessageService messageService;


    public void sendSocketMessage(SocketIOClient senderClient, Message message, String room) {
        for (
                SocketIOClient client : senderClient.getNamespace().getRoomOperations(room).getClients()) {
            if (!client.getSessionId().equals(senderClient.getSessionId())) {
                client.sendEvent("read_message", message);
            }
        }
    }

    public void saveMessage(SocketIOClient senderClient, Message message) {



        Message storedMessage = messageService.saveMessage(Message.builder()
                .messageType(message.getMessageType())
                .content(message.getContent())
                .room(message.getRoom())
                .contentType(message.getContentType())
                .username(message.getUsername())
                .build());
        sendSocketMessage(senderClient, storedMessage, message.getRoom());
    }

//    public void saveInfoMessage(SocketIOClient senderClient, String message, String room ,String username ,String contentType ) {
//        Message storedMessage = messageService.saveMessage(Message.builder()
//                .content(message)
//                .room(room)
//                .contentType(contentType)
//                .username(username)
//                .build());
//        sendSocketMessage(senderClient, storedMessage, room);
//    }
}