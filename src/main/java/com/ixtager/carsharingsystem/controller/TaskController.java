package com.ixtager.carsharingsystem.controller;

import com.ixtager.carsharingsystem.enumerating.TaskStatus;
import com.ixtager.carsharingsystem.model.Task;
import com.ixtager.carsharingsystem.request.UpdateTaskStatus;
import com.ixtager.carsharingsystem.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/active")
    public ResponseEntity<List<Task>> getActiveTasks() {
        List<Task> activeTasks = taskService.getTasksByStatus(TaskStatus.IN_PROGRESS);
        return ResponseEntity.ok(activeTasks);
    }

    @PostMapping("/change_status")
    public ResponseEntity<Task> changeTaskStatus(@RequestBody UpdateTaskStatus updateTaskStatus) {
        Task newTask = taskService.updateTaskStatusByRequest(updateTaskStatus);
        if (newTask == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(newTask);
    }
}
