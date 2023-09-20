package com.rescue.vscube.invitation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/invite")
@RestController
public class InvitationController {

    @Autowired
    private InvitationService invitationService;
    @PostMapping("")
    public ResponseEntity<Invitation> addInvitation(@RequestBody InvitationDTO invitationDTO){
        return new ResponseEntity<>(invitationService.addInvitation(invitationDTO), HttpStatus.OK);
    }

    @GetMapping("/agency/{agency_id}")
    public ResponseEntity<List<Invitation>> getInvitationAgencies(@PathVariable Long agency_id){
        return new ResponseEntity<>(invitationService.getAgencyInvites(agency_id),HttpStatus.OK);
    }

    @PutMapping("/{invitation_id}/status/{status}")
    public ResponseEntity<Void> changeStatus(@PathVariable Long invitation_id, @PathVariable String status){
        invitationService.changeStatus(invitation_id,status);
        return ResponseEntity.ok(null);
    }
}
