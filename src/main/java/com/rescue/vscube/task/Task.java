package com.rescue.vscube.task;

import com.rescue.vscube.event.Event;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Setter
@Getter
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long task_id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(nullable = false)
    private String description;

    @Column(nullable = true)
    private Timestamp time_created;

    @Column(nullable = false)
    private String status;

    @PrePersist
    public void setDefaultValues() {
        if (time_created == null) {
            time_created = new Timestamp(System.currentTimeMillis());
        }
    }

}
