package com.backend.Retrospect.sockets;
import com.backend.Retrospect.room.repository.IRoomRepository;
import com.backend.Retrospect.roomToUser.dto.UserRoomJoinDTO;
import com.backend.Retrospect.roomToUser.repository.IRoomToUserRepository;
import com.backend.Retrospect.roomToUser.service.RoomToUserService;
import com.backend.Retrospect.sockets.constants.Constants;
import com.backend.Retrospect.sockets.entiry.Message;
import com.backend.Retrospect.user.entity.UserEntity;
import com.backend.Retrospect.user.repository.IUserRepository;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
////            roomToUserService.UserJoinedRoom()
//            UserEntity userEntity = userRepository.findByName(username);
//            LocalDateTime localDateTime  = LocalDateTime.now();
//
//
//            UserRoomJoinDTO userRoomJoinDTO  = new UserRoomJoinDTO();
//            userRoomJoinDTO.setRoomId(Long.parseLong(room));
//            userRoomJoinDTO.setUserId(userEntity.getUserId());
//
//            roomToUserService.UserJoinedRoom(userRoomJoinDTO);

            String contentType = "connected";
            client.joinRoom(room);
            socketService.saveInfoMessage(client, String.format(Constants.WELCOME_MESSAGE, username), room ,username ,contentType);
            log.info("Socket ID[{}] - room[{}] - username[{}] - content[{}] connected to chat", client.getSessionId().toString(), room, username, contentType);
        };
    }




    private DisconnectListener onDisconnected() {
        return client -> {
            var params = client.getHandshakeData().getUrlParams();
            String room = String.join("", params.get("room"));
            String username = String.join("", params.get("username"));
            String contentType = "disconnected";
            socketService.saveInfoMessage(client, String.format(Constants.DISCONNECT_MESSAGE, username), room ,username , contentType);
            log.info("Socket ID[{}] - room[{}] - username [{}]  disconnected to chat module through", client.getSessionId().toString(), room, username);
        };
    }

}