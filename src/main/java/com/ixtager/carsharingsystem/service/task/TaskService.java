package com.ixtager.carsharingsystem.service.task;

import com.ixtager.carsharingsystem.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> getTasksByVIN(String vin);

    Task saveTask(Task task);

    Task updateTask(String vin, Task task);

    void deleteTask(Long id);
}
