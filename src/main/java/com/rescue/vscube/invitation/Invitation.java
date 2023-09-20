
package com.rescue.vscube.invitation;

import com.rescue.vscube.agency.Agency;
import com.rescue.vscube.event.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="invitation")

public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;

    @Column(name="status")
    private String status;

    @Column(name="time_created")
    private Timestamp timeCreated;

    @PrePersist
    public void setDefaultValues() {
        if (status == null) {
            status = "invited";
        }
        if (timeCreated == null) {
            timeCreated = new Timestamp(System.currentTimeMillis());
        }
    }
}
