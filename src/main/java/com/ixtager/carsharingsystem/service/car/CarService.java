package com.ixtager.carsharingsystem.service.car;

import com.ixtager.carsharingsystem.enumerating.CarStatus;
import com.ixtager.carsharingsystem.model.Car;
import com.ixtager.carsharingsystem.request.CreateCarRequest;
import com.ixtager.carsharingsystem.request.TelemetryUpdateRequest;
import com.ixtager.carsharingsystem.request.UpdateCarRequest;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    List<Car> getCarsByStatus(CarStatus status);
    List<Car> getCatsByModel(String model);
    Car getCarById(Long id);

    Car addNewCar(CreateCarRequest createCarRequest);

    Car updateCarByUpdateCarRequest(String vin, UpdateCarRequest updateCarRequest);
    Car updateCarStatus(String vin, CarStatus status);

    void deleteCar(String vin);
}
