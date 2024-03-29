package com.rescue.vscube.task;


import com.rescue.vscube.models.Coordinate;
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
    public ResponseEntity<Task> addTask(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.addTask(taskDTO));
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
    public ResponseEntity<Void> addAgency(@PathVariable Long task_id, @PathVariable Long agency_id,@RequestBody CoordinateDTO coordinates){
        taskService.addAgency(task_id,agency_id,coordinates.getRegion(),coordinates.getCoordinates());
        return ResponseEntity.ok().build();
    }
    @GetMapping("/event/{event_id}")
    public ResponseEntity<List<TaskDTO>> getTasksEvent(@PathVariable Long event_id){
        return ResponseEntity.ok(taskService.getTasksEvent(event_id));
    }


}
