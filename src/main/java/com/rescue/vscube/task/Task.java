package com.rescue.vscube.task;

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

    @Column(nullable = false)
    private Long event_id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = true)
    private Timestamp time_created;

    @Column(nullable = false)
    private String status;

}
