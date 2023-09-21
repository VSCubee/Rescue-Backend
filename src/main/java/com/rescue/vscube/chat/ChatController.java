package com.rescue.vscube.chat;

import com.rescue.vscube.Message.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ChatController {
    @MessageMapping("/chat/register/{event_id}")
    @SendTo("/topic/{event_id}")
    public Message register(@Payload Message chatMessage, SimpMessageHeaderAccessor headerAccessor, @PathVariable Long event_id) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getAgency().getName());
        return chatMessage;
    }

    @MessageMapping("/chat/send/{event_id}")
    @SendTo("/topic/{event_id}")
    public Message sendMessage(@Payload Message chatMessage, @PathVariable Long event_id) {
        return chatMessage;
    }
}
