package com.ixtager.carsharingsystem.jpa;

import com.ixtager.carsharingsystem.enumerating.CarStatus;
import com.ixtager.carsharingsystem.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByStatus(CarStatus status);
    List<Car> findAllByModel(String model);
}
