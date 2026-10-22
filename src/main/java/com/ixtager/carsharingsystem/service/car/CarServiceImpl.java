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
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
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
    public Car addCar(Car car) {
        log.info("Add new car with new VIN: {}", car.getVIN());
        return carRepository.save(car);
    }

    @Override
    @Transactional
    public Car updateCarByUpdateRequest(TelemetryUpdateRequest telemetryUpdateRequest) {
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

        log.info("Car with VIN: {} was updated",  foundedCar.getVIN());
        return carRepository.save(foundedCar);
    }

    @Override
    @Transactional
    public Car updateCar(String vin, Car car) {
        Car foundedCar =  carRepository.findCarByVIN(car.getVIN())
                .orElse(null);
        if (foundedCar == null) return null;

        foundedCar.setVIN(car.getVIN());
        foundedCar.setModel(car.getModel());
        foundedCar.setStatus(car.getStatus());
        foundedCar.setLicensePlateNumber(foundedCar.getLicensePlateNumber());
        foundedCar.setTasks(car.getTasks());
        foundedCar.setLastLatitude(car.getLastLatitude());

        return carRepository.save(foundedCar);
    }

    @Override
    @Transactional
    public void deleteCar(String vin) {
        carRepository.deleteCarByVIN(vin);
        log.info("Car with VIN: {} was deleted", vin);
    }
}
