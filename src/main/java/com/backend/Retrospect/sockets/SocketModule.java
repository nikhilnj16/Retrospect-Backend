package com.backend.Retrospect.sockets;

import com.backend.Retrospect.room.repository.IRoomRepository;
import com.backend.Retrospect.roomToUser.repository.IRoomToUserRepository;
import com.backend.Retrospect.roomToUser.service.RoomToUserService;
import com.backend.Retrospect.sockets.entiry.Message;
import com.backend.Retrospect.user.repository.IUserRepository;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
@Slf4j
public class SocketModule {

    @Autowired
    IRoomToUserRepository roomToUserRepository;
    @Autowired
    IRoomRepository roomRepository;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    SocketIOServer server;
    @Autowired
    SocketService socketService;

    @Autowired
    private RoomToUserService roomToUserService;


    public SocketModule(SocketIOServer server, SocketService socketService) {
        this.server = server;
        this.socketService = socketService;
        server.addConnectListener(onConnected());
        server.addDisconnectListener(onDisconnected());
        server.addEventListener("send_message", Message.class, onChatReceived());

    }


    private DataListener<Message> onChatReceived() {
        return (senderClient, data, ackSender) -> {
            log.info("Received message from client {}: {} : {}", senderClient.getSessionId(), data.getContent() ,data.getContentType());
            socketService.saveMessage(senderClient, data);
            // Handle sending the message to other clients
            server.getRoomOperations(data.getRoom()).sendEvent("receive_message", data);
        };
    }


    private ConnectListener onConnected() {
        return (client) -> {
            var params = client.getHandshakeData().getUrlParams();
            String room = String.join("", params.get("room"));
            String username = String.join("", params.get("username"));
//            String contentype = "connected";
            client.joinRoom(room);

            // Emit a message to all clients in the room about the new user
            server.getRoomOperations(room).sendEvent("user_join" ,username);

            log.info("Socket ID[{}] - room[{}] - username[{}] connected to chat", client.getSessionId().toString(), room, username);
        };

    }

    private DisconnectListener onDisconnected() {
        return client -> {
            var params = client.getHandshakeData().getUrlParams();
            String room = String.join("", params.get("room"));
            String username = String.join("", params.get("username"));

            server.getRoomOperations(room).sendEvent("user_exit" ,username);

            log.info("Socket ID[{}] - room[{}] - username [{}] disconnected from chat", client.getSessionId().toString(), room, username);
        };
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