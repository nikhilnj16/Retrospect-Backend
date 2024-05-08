package com.backend.Retrospect.sockets;

import com.backend.Retrospect.room.entity.RoomEntity;
import com.backend.Retrospect.room.repository.IRoomRepository;
import com.backend.Retrospect.roomToUser.entity.RoomToUserEntity;
import com.backend.Retrospect.roomToUser.entity.RoomToUserId;
import com.backend.Retrospect.roomToUser.repository.IRoomToUserRepository;
import com.backend.Retrospect.sockets.constants.Constants;
import com.backend.Retrospect.sockets.entiry.Message;
import com.backend.Retrospect.user.entity.UserEntity;
import com.backend.Retrospect.user.repository.IUserRepository;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


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
//            String room = client.getHandshakeData().getSingleUrlParam("room");
//            String username = client.getHandshakeData().getSingleUrlParam("room");
            var params = client.getHandshakeData().getUrlParams();
            String room = String.join("", params.get("room"));
            String username = String.join("", params.get("username"));
            Optional<RoomEntity> roomEntityOptional = roomRepository.findById(Long.valueOf(room));
            UserEntity userEntity = userRepository.findByName(username);

            if (roomEntityOptional.isPresent() && userEntity != null) {
                RoomEntity roomEntity = roomEntityOptional.get();
                LocalDate timeStamp = LocalDate.now(); // Example LocalDate

                // Define the format you want for your string representation
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                // Convert the LocalDate to a string using the defined format
                String timeStampString = timeStamp.format(formatter);
                RoomToUserId roomToUserId = new RoomToUserId(roomEntity, userEntity, timeStampString);

                RoomToUserEntity roomToUserEntity = new RoomToUserEntity();
                if (!roomToUserRepository.existsById(roomToUserId)) {
                    roomToUserEntity.setId(roomToUserId);
                    roomToUserRepository.save(roomToUserEntity);
                }
                String contentType = "connected";
                client.joinRoom(room);
                socketService.saveInfoMessage(client, String.format(Constants.WELCOME_MESSAGE, username), room ,username , contentType );
                log.info("Socket ID[{}] - room[{}] - username [{}]  Connected to chat module through", client.getSessionId().toString(), room, username);
            } else{
                log.error("Room or User not found");
            }

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