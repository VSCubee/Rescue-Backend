package com.rescue.vscube.task;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;


    @GetMapping("")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getALLTasks());
    }

    @PostMapping("")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        taskService.addTask(task);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/{task_id}")
    public ResponseEntity<TaskDTO> getEvent(@PathVariable Long task_id){
        return ResponseEntity.ok(taskService.findOne(task_id));
    }

    @PutMapping("")
    public ResponseEntity<Task> updateTask(@RequestBody Task task){
        return ResponseEntity.ok(taskService.updateTask(task));
    }

    @PutMapping("/{task_id}/agency/{agency_id}")
    public ResponseEntity<Void> addAgency(@PathVariable Long task_id,@PathVariable Long agency_id){
        taskService.addAgency(task_id,agency_id);
        return ResponseEntity.ok().build();
    }


}
