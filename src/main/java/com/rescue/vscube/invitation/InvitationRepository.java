package com.rescue.vscube.invitation;
import com.rescue.vscube.agency.Agency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    List<Invitation> findByAgencyAndStatus(Agency agency, String status);
}
