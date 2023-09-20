package com.rescue.vscube.invitation;

import com.rescue.vscube.agency.Agency;
import com.rescue.vscube.agency.AgencyRepository;
import com.rescue.vscube.event.Event;
import com.rescue.vscube.event.EventRepository;
import com.rescue.vscube.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitationService {
    @Autowired
    private InvitationRepository invitationRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private EventService eventService;

    public Invitation addInvitation(InvitationDTO invitationDTO){
        Long eventId = invitationDTO.getEventId();
        Event event = eventRepository.findById(invitationDTO.getEventId()).get();
        Agency agency = agencyRepository.findById(invitationDTO.getAgencyId()).get();

        Invitation invitation = new Invitation();
        invitation.setAgency(agency);
        invitation.setEvent(event);

        return invitationRepository.save(invitation);
    }

    public List<Invitation> getAgencyInvites(Long agencyId){
        Agency agency = agencyRepository.findById(agencyId).get();
        return invitationRepository.findByAgencyAndStatus(agency,"invited");
    }

    public void changeStatus(Long invitationId, String status){
        Invitation invitation = invitationRepository.findById(invitationId).get();
        invitation.setStatus(status);
        if(status.equals("accepted")){
            eventService.addAgency(invitation.getEvent().getId(),invitation.getAgency().getId());
        }
        invitationRepository.save(invitation);

    }
}
