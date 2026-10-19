package com.ixtager.carsharingsystem.service.car;

import com.ixtager.carsharingsystem.enumerating.CarStatus;
import com.ixtager.carsharingsystem.enumerating.TaskPriority;
import com.ixtager.carsharingsystem.jpa.CarRepository;
import com.ixtager.carsharingsystem.model.Car;
import com.ixtager.carsharingsystem.model.Task;
import com.ixtager.carsharingsystem.request.TelemetryUpdateRequest;
import com.ixtager.carsharingsystem.service.task.TaskService;
import com.ixtager.carsharingsystem.util.TaskUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    @Value("${car.max.mileage}")
    private int MAX_MILEAGE;

    @Value("${car.min.fuel-level-percent}")
    private int MIN_FUEL_LEVEL_PRECENT;

    private final TaskUtil taskUtil;
    private final CarRepository carRepository;
    private final TaskService taskService;

    @Override
    public List<Car> getCarsByStatus(CarStatus status) {
        return carRepository.findAllByStatus(status);
    }

    @Override
    public List<Car> getCatsByModel(String model) {
        return carRepository.findAllByModel(model);
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id)
                .orElse(null);
    }

    @Override
    @Transactional
    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    @Transactional
    public Car updateCarByUpdateRequest(TelemetryUpdateRequest  telemetryUpdateRequest) {
        Car foundedCar = carRepository.findCarByVIN(telemetryUpdateRequest.getVin())
                .orElse(null);
        if (foundedCar == null) return null;

        foundedCar.setVIN(telemetryUpdateRequest.getVin());
        foundedCar.setMileage(telemetryUpdateRequest.getCurrentMileage());
        foundedCar.setFuelLevel(telemetryUpdateRequest.getFuelLevel());
        foundedCar.setLastLatitude(telemetryUpdateRequest.getLatitude());
        foundedCar.setLastLongitude(telemetryUpdateRequest.getLongitude());

        if (telemetryUpdateRequest.getCurrentMileage() > MAX_MILEAGE) {
            Task newTask;
            foundedCar.setStatus(CarStatus.MAINTENANCE);
            if (telemetryUpdateRequest.getFuelLevel() < MIN_FUEL_LEVEL_PRECENT) {
                newTask = taskUtil.createTask(
                        "Плановое ТО: превышен лимит пробега", TaskPriority.HIGH, foundedCar);
            }
            else {
                newTask = taskUtil.createTask("Плановое ТО: превышен лимит пробега",
                        TaskPriority.MEDIUM, foundedCar);
            }
            taskService.saveTask(newTask);
        }

        return carRepository.save(foundedCar);
    }

    @Override
    @Transactional
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
