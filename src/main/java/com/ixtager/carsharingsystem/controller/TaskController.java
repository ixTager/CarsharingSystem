package com.ixtager.carsharingsystem.controller;

import com.ixtager.carsharingsystem.enumerating.TaskStatus;
import com.ixtager.carsharingsystem.model.Task;
import com.ixtager.carsharingsystem.request.UpdateTaskStatus;
import com.ixtager.carsharingsystem.service.task.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Tag(name = "Task controller")
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/active")
    @PreAuthorize("hasAnyRole('DISPATCHER','MECHANIC')")
    @Operation(
            summary = "Get active tasks",
            description = "Return list of tasks, which is active"
    )
    @ApiResponse(responseCode = "200", description = "Getting active tasks is successful")
    public ResponseEntity<List<Task>> getActiveTasks() {
        List<Task> activeTasks = taskService.getTasksByStatus(TaskStatus.IN_PROGRESS);
        return ResponseEntity.ok(activeTasks);
    }

    @PostMapping("/change_status")
    @PreAuthorize("hasAuthority('ROLE_MECHANIC')")
    @ApiResponse(responseCode = "200", description = "Changing task status is successful")
    @ApiResponse(responseCode = "404", description = "Task was not found")
    public ResponseEntity<Task> changeTaskStatus(@Valid @RequestBody UpdateTaskStatus updateTaskStatus) {
        Task newTask = taskService.updateTaskStatusByRequest(updateTaskStatus);
        if (newTask == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(newTask);
    }
}
