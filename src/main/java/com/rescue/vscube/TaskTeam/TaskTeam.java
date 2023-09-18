package com.rescue.vscube.TaskTeam;

import com.rescue.vscube.agency.Agency;
import com.rescue.vscube.task.Task;
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
@Table(name="task_team",uniqueConstraints = @UniqueConstraint(columnNames = {"task_id", "agency_id"}))

public class TaskTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;
}
