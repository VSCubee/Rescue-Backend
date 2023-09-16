package com.rescue.vscube.task;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
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





}
