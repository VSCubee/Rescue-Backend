package com.rescue.vscube.chat;

import com.rescue.vscube.Message.Message;
import com.rescue.vscube.Message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private MessageService messageService;

    @MessageMapping("/message")

    public void receiveMessage(@Payload Message message){
        String destination = "/chatroom/public/" + message.getEvent().getId();
        simpMessagingTemplate.convertAndSend(destination,message);
        messageService.addMessageSocket(message);
    }

    @MessageMapping("/private-message")
    public Message recMessage(@Payload Message message){
        simpMessagingTemplate.convertAndSendToUser(message.getAgency().getName(),"/private",message);
        System.out.println(message.toString());
        return message;
    }
}

