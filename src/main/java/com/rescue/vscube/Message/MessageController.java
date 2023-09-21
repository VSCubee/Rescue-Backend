package com.rescue.vscube.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping("")
    private ResponseEntity<Message> add(@RequestBody Message message){
        return new ResponseEntity<>(messageService.addMessage(message), HttpStatus.OK);
    }

    @GetMapping("")
    private ResponseEntity<List<Message>> getMessages(){
        return new ResponseEntity<>(messageService.getMessages(), HttpStatus.OK);
    }

    @GetMapping("event/{event_id}")
    private ResponseEntity<List<Message>> getMessagesEvent(@PathVariable Long event_id ){
        return new ResponseEntity<>(messageService.getMessagesEvent(event_id), HttpStatus.OK);
    }

}
