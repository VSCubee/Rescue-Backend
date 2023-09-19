package com.rescue.vscube.TaskTeam;

import com.rescue.vscube.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskTeamRepository extends JpaRepository<TaskTeam, Long> {
    List<TaskTeam> findAllByTask(Task task);
}
