package com.ixtager.carsharingsystem.service.task;

import com.ixtager.carsharingsystem.enumerating.TaskStatus;
import com.ixtager.carsharingsystem.jpa.TaskRepository;
import com.ixtager.carsharingsystem.model.Task;
import com.ixtager.carsharingsystem.request.UpdateTaskStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements  TaskService {
    private final TaskRepository taskRepository;

    @Override
    public List<Task> getTasksByVIN(String vin) {
        return taskRepository.findAllByCar_VIN(vin);
    }

    @Override
    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findAllByStatus(status);
    }

    @Override
    @Transactional
    public Task saveTask(Task task) {
        log.info("Save new Task with id: {}", task.getId());
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public Task updateTaskStatusByRequest(UpdateTaskStatus updateTaskStatus) {
        Task foundedTask = taskRepository.findTaskByCar_VIN(updateTaskStatus.getVin())
                        .orElse(null);
        if (foundedTask == null) return null;
        foundedTask.setStatus(updateTaskStatus.getStatus());
        log.info("Update Task Status with id: {}. Current Status - {}",
                foundedTask.getId(),  updateTaskStatus.getStatus());
        return taskRepository.save(foundedTask);
    }

    @Override
    @Transactional
    public Task updateTask(String vin, Task task) {
        Task foundedTask = taskRepository.findTaskByCar_VIN(vin)
                .orElse(null);
        if (foundedTask == null) return taskRepository.save(task);

        foundedTask.setStatus(task.getStatus());
        foundedTask.setDescription(task.getDescription());
        foundedTask.setPriority(task.getPriority());

        log.info("Task updated to car with VIN: {}", vin);
        return foundedTask;
    }

    @Override
    @Transactional
    public void deleteTask(Long id) {
        log.info("Delete task with id: {}", id);
        taskRepository.deleteById(id);
    }

}
