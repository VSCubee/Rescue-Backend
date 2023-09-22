package com.rescue.vscube.event;


import com.rescue.vscube.agency.Agency;
import com.rescue.vscube.agency.AgencyRepository;
import com.rescue.vscube.agency.AgencyService;
import com.rescue.vscube.event_team.EventTeam;
import com.rescue.vscube.event_team.EventTeamRepository;
import com.rescue.vscube.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private AgencyService agencyService;

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public  Event addEvent(EventDTO eventDTO){
        Agency agency = agencyRepository.findById(eventDTO.getCreatedBy()).get();

        Event event = new Event();
        event.setCoordinates(eventDTO.getCoordinates());
        event.setCreatedOn(eventDTO.getCreatedOn());
        event.setCoordinates(eventDTO.getCoordinates());
        event.setDescription(eventDTO.getDescription());
        event.setCreatedBy(agency);
        event.setName(eventDTO.getName());
        event.setRegion(eventDTO.getRegion());

        agencyService.updateActivity(agency.getId());
        return eventRepository.save(event);
    }

    public EventDTO getEventById(Long eventId){
        Optional<Event> event = eventRepository.findById(eventId);
        Event ev = event.orElse(null);

        EventDTO eventDTO= new EventDTO(eventId,ev.getName(),
                ev.getCreatedOn(),
                ev.getDescription(),
                ev.getCoordinates(),
                ev.getCreatedBy().getId(),
                ev.getRegion(),
                getEventAgencies(ev));

        return eventDTO;
    }

    public void addAgency(Long eventId,Long agencyId){
        Event event = eventRepository.findById(eventId).get();

        Optional<Agency> agency2 = agencyRepository.findById(agencyId);
        Agency agency = agency2.orElse(null);

        EventTeam eventTeam = new EventTeam();

        eventTeam.setEvent(event);
        eventTeam.setAgency(agency);

        eventTeamRepository.save(eventTeam);
        agencyService.updateActivity(agencyId);
    }

    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Agency> getEventAgencies(Event event){
        List<EventTeam> teams = eventTeamRepository.findAllByEvent(event);
        List<Agency> agencies= new ArrayList<>();

        for(EventTeam eventTeam : teams)
            agencies.add(eventTeam.getAgency());

        return agencies;
    }
}
