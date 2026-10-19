package com.ixtager.carsharingsystem.util;

import com.ixtager.carsharingsystem.enumerating.TaskPriority;
import com.ixtager.carsharingsystem.enumerating.TaskStatus;
import com.ixtager.carsharingsystem.model.Car;
import com.ixtager.carsharingsystem.model.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TaskUtil {
    public Task createTask(String description, TaskPriority priority, Car car) {
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Task.builder()
                .description(description)
                .car(car)
                .status(TaskStatus.IN_PROGRESS)
                .priority(priority)
                .createdAt(LocalDateTime.now().format(formatter))
                .build();
    }
}
