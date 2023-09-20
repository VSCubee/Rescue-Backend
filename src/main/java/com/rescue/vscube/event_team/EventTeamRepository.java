package com.rescue.vscube.event_team;

import com.rescue.vscube.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventTeamRepository extends JpaRepository<EventTeam, Long> {
    List<EventTeam> findAllByEvent(Event event);
}