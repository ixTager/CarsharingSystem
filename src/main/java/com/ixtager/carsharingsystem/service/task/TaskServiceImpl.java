package com.ixtager.carsharingsystem.service.task;

import com.ixtager.carsharingsystem.jpa.TaskRepository;
import com.ixtager.carsharingsystem.model.Task;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements  TaskService {
    private final TaskRepository taskRepository;

    @Override
    public List<Task> getTasksByVIN(String vin) {
        return taskRepository.findAllByCar_VIN(vin);
    }

    @Override
    @Transactional
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public Task updateTask(String vin, Task task) {
        Task foundedTask = taskRepository.findTaskByCar_VIN(vin)
                .orElse(null);
        if (foundedTask == null) return taskRepository.save(task);

        //Todo

        return foundedTask;
    }

    @Override
    @Transactional
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

}
