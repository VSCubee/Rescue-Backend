package com.rescue.vscube.task;

import com.rescue.vscube.agency.Agency;
import lombok.*;
import java.sql.Timestamp;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskDTO {

    private Long task_id;
    private Long event_id;

    private String description;

    private Timestamp time_created;

    private String status;

    List<AgencyDTO> assignedTeams;

}
