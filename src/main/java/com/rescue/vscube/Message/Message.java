package com.rescue.vscube.Message;


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
@Table(name="message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;

    @Column(name="content")
    private String content;

    @Column(name="sent_on")
    private Timestamp sentOn;

}
