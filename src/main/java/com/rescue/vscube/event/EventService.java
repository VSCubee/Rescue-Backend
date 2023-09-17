package com.rescue.vscube.event;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public  Event addEvent(Event event){
        return eventRepository.save(event);
    }

    public Event getEventById(Long eventId){
        Optional<Event> event = eventRepository.findById(eventId);
        return event.orElse(null);
    }
}
