package com.ixtager.carsharingsystem.jpa;

import com.ixtager.carsharingsystem.enumerating.TaskStatus;
import com.ixtager.carsharingsystem.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    Optional<Task> findTaskByCar_VIN(String vin);
    List<Task> findAllByCar_VIN(String vin);
    List<Task> findAllByStatus(TaskStatus status);
}
