package com.rescue.vscube.Message;

import com.rescue.vscube.agency.Agency;
import com.rescue.vscube.event.Event;
import com.rescue.vscube.event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private EventRepository eventRepository;

    public Message addMessage(Message message){
        return messageRepository.save(message);
    }

    public Message addMessageSocket(Message message){
        if(message.getAgency() == null || message.getEvent() == null)
            return null;
        Message mssg = new Message();

        mssg.setAgency(message.getAgency());
        mssg.setContent(message.getContent());
        mssg.setEvent(message.getEvent());
        mssg.setContent(message.getContent());
        mssg.setSentOn(message.getSentOn());

        return messageRepository.save(mssg);
    }

    public List<Message> getMessages(){
        return messageRepository.findAll();
    }

    public List<Message> getMessagesEvent(Long eventId){
        Event event = eventRepository.findById(eventId).get();
        return messageRepository.findByEvent(event);
    }
}
