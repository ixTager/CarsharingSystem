package com.ixtager.carsharingsystem.service.telemetry;

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
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class TelemetryServiceImpl implements  TelemetryService {
    @Value("${car.max.mileage}")
    private Integer MAX_MILEAGE;

    @Value("${car.min.fuel-level-percent}")
    private Integer MIN_FUEL_LEVEL_PRECENT;

    private final TaskService taskService;
    private final TaskUtil  taskUtil;
    private final CarRepository carRepository;

    @Override
    @Transactional
    public Car updateCarTelemetry(TelemetryUpdateRequest telemetryUpdateRequest) {
        Car foundedCar = carRepository.findCarByVIN(telemetryUpdateRequest.getVin())
                .orElse(null);
        if (foundedCar == null) return null;

        foundedCar.setMileage(telemetryUpdateRequest.getCurrentMileage());
        foundedCar.setFuelLevel(telemetryUpdateRequest.getFuelLevel());
        foundedCar.setLastLatitude(telemetryUpdateRequest.getLatitude());
        foundedCar.setLastLongitude(telemetryUpdateRequest.getLongitude());

        if (foundedCar.getMileage() > MAX_MILEAGE) {
            Task newTask;
            foundedCar.setStatus(CarStatus.MAINTENANCE);
            if (foundedCar.getFuelLevel() < MIN_FUEL_LEVEL_PRECENT) {
                newTask = taskUtil.createTask(
                        "Плановое ТО: превышен лимит пробега", TaskPriority.HIGH, foundedCar);
            }
            else {
                newTask = taskUtil.createTask("Плановое ТО: превышен лимит пробега",
                        TaskPriority.MEDIUM, foundedCar);
            }
            taskService.saveTask(newTask);
            log.info("Car win VIN: {}, change status. Current Status - {}",
                    foundedCar.getVIN(), foundedCar.getStatus());
        }

        log.info("Car telemetry with VIN: {} was updated", foundedCar.getVIN());
        return carRepository.save(foundedCar);
    }
}
