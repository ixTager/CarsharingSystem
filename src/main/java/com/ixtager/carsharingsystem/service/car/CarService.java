package com.ixtager.carsharingsystem.service.car;

import com.ixtager.carsharingsystem.enumerating.CarStatus;
import com.ixtager.carsharingsystem.model.Car;
import com.ixtager.carsharingsystem.request.TelemetryUpdateRequest;

import java.util.List;

public interface CarService {
    List<Car> getCarsByStatus(CarStatus status);
    List<Car> getCatsByModel(String model);
    Car getCarById(Long id);

    Car createCar(Car car);

    Car updateCarByUpdateRequest(TelemetryUpdateRequest telemetryUpdateRequest);

    void deleteCar(Long id);
}
