package com.ixtager.carsharingsystem.service;

import com.ixtager.carsharingsystem.enumerating.TaskStatus;
import com.ixtager.carsharingsystem.model.Task;

import java.util.List;

public interface MechanicService {
    List<Task> getActiveTasks();

    Task changeTaskStatus(Task task, TaskStatus newStatus);
}
