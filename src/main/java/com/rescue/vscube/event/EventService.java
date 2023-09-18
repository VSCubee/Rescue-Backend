package com.rescue.vscube.event;


import com.rescue.vscube.agency.Agency;
import com.rescue.vscube.agency.AgencyRepository;
import com.rescue.vscube.event_team.EventTeam;
import com.rescue.vscube.event_team.EventTeamRepository;
import com.rescue.vscube.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventTeamRepository eventTeamRepository;

    @Autowired
    private AgencyRepository agencyRepository;

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

    public void addAgency(Long eventId,Long agencyId){
        Event event = getEventById(eventId);

        Optional<Agency> agency2 = agencyRepository.findById(agencyId);
        Agency agency = agency2.orElse(null);

        EventTeam eventTeam = new EventTeam();
        eventTeam.setEvent(event);
        eventTeam.setAgency(agency);

        eventTeamRepository.save(eventTeam);
    }

    public Event updateEvent(Event event) {
        return eventRepository.save(event);

    }
}
