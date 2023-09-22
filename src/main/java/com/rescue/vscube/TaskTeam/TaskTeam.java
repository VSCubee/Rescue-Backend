package com.rescue.vscube.TaskTeam;

import com.rescue.vscube.agency.Agency;
import com.rescue.vscube.event.CoordinateListConverter;
import com.rescue.vscube.models.Coordinate;
import com.rescue.vscube.task.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="task_team")

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

    private String region;

    @ElementCollection
    @Convert(converter = CoordinateListConverter.class)
    private List<Coordinate> coordinates;
}
