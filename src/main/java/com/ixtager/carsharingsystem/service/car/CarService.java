package com.ixtager.carsharingsystem.service.car;

import com.ixtager.carsharingsystem.enumerating.CarStatus;
import com.ixtager.carsharingsystem.model.Car;

import java.util.List;

public interface CarService {
    List<Car> getCarsByStatus(CarStatus status);
    List<Car> getCatsByModel(String model);
    Car getCarById(Long id);

    Car createCar(Car car);

    Car updateCar(Long id, Car car);

    void deleteCar(Long id);
}
