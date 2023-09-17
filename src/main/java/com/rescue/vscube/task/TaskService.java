package com.rescue.vscube.task;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getALLTasks() {
        return taskRepository.findAll();
    }

    public Task addTask(Task task) {

        return taskRepository.save(task);
    }

    public Task updateStatus(Task task) {
        task.setStatus("Completed");
        return task;
    }

    public Task findOne(Long taskId){
        Optional<Task> task = taskRepository.findById(taskId);
        return task.orElse(null);
    }


}
