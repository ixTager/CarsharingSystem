package com.ixtager.carsharingsystem.service.task;

import com.ixtager.carsharingsystem.enumerating.TaskStatus;
import com.ixtager.carsharingsystem.model.Task;
import com.ixtager.carsharingsystem.request.UpdateTaskStatus;

import java.util.List;

public interface TaskService {
    List<Task> getTasksByVIN(String vin);
    List<Task> getTasksByStatus(TaskStatus status);

    Task saveTask(Task task);

    Task updateTask(String vin, Task task);
    Task updateTaskStatusByRequest(UpdateTaskStatus updateTaskStatus);

    void deleteTask(Long id);
}
