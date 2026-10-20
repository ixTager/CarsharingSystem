package com.ixtager.carsharingsystem.service.task;

import com.ixtager.carsharingsystem.enumerating.TaskStatus;
import com.ixtager.carsharingsystem.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> getTasksByVIN(String vin);
    List<Task> getTasksByStatus(TaskStatus status);

    Task saveTask(Task task);

    Task updateTask(String vin, Task task);
    Task updateTaskStatus(TaskStatus taskStatus, Task task);

    void deleteTask(Long id);
}
