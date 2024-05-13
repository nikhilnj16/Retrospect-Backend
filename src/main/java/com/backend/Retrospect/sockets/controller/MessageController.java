package com.backend.Retrospect.sockets.controller;


import com.backend.Retrospect.sockets.entiry.Message;
import com.backend.Retrospect.sockets.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MessageController {

    private final MessageService messageService;

    @CrossOrigin
    @GetMapping("/{room}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String room) {
        return ResponseEntity.ok(messageService.getMessages(room));
    }

//    @GetMapping("/analysisMessage/{room}")
//    public ResponseEntity<HashMap<String,Integer>> analysisMessages(@PathVariable String room)
//    {
//        return ResponseEntity.ok(messageService.analysisMessages(room));
//    }


}