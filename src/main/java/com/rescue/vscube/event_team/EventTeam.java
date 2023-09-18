package com.rescue.vscube.event_team;

import com.rescue.vscube.agency.Agency;
import com.rescue.vscube.event.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="event_team")
public class EventTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @MapsId("eventId")
    @JoinColumn(name = "id")
    private Event event;

    @ManyToOne
    @MapsId("agencyId")
    @JoinColumn(name = "id")
    private Agency agency;
}
