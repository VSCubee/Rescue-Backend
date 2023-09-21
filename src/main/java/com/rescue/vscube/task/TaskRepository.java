package com.rescue.vscube.task;

import com.rescue.vscube.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Long> {
    public List<Task> findByEvent(Event event);
}
