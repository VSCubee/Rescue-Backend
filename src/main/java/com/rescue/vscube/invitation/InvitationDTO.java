package com.rescue.vscube.invitation;

import com.rescue.vscube.agency.Agency;
import com.rescue.vscube.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InvitationDTO {
    private Long agencyId;
    private Long eventId;

}
