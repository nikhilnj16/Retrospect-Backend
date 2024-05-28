package com.backend.Retrospect.sockets.controller;


import com.backend.Retrospect.sockets.entiry.Message;
import com.backend.Retrospect.sockets.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class MessageController {

    private final MessageService messageService;

    @CrossOrigin
    @GetMapping("/message/{room}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String room) {
        return ResponseEntity.ok(messageService.getMessages(room));
    }

    @PutMapping("/message/like/{messageId}")
    public ResponseEntity<Integer> likeMessage(@PathVariable Long messageId, @RequestBody String user) {
        Integer likeCountResponse= messageService.likeMessage(messageId,user);
        return ResponseEntity.ok(likeCountResponse);
    }


//    @GetMapping("/analysisMessage/{room}")
//    public ResponseEntity<HashMap<String ,Integer>> analysisMessages(@PathVariable String room){
//        return ResponseEntity.ok(messageService.analysisMessages(room));
//    }



    @DeleteMapping("/message/delete/{messageId}")
    public HashMap<String, String > deleteMessage(@PathVariable Long messageId) {
        return messageService.deleteMessageById(messageId);
    }


}