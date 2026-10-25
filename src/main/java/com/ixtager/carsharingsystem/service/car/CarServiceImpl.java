package com.ixtager.carsharingsystem.service.car;

import com.ixtager.carsharingsystem.enumerating.CarStatus;
import com.ixtager.carsharingsystem.jpa.CarRepository;
import com.ixtager.carsharingsystem.model.Car;
import com.ixtager.carsharingsystem.request.CreateCarRequest;
import com.ixtager.carsharingsystem.request.UpdateCarRequest;
import com.ixtager.carsharingsystem.util.CarUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarUtil carUtil;
    private final CarRepository carRepository;

    @Override
    public List<Car> getAllCars() { return carRepository.findAll(); }

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
    public Car addNewCar(CreateCarRequest createCarRequest) {
        Car newCar = carUtil.createCarEntityByCarRequest(createCarRequest);
        log.info("Add new car with new VIN: {}", newCar.getVIN());
        return newCar;
    }



    @Override
    @Transactional
    public Car updateCarByUpdateCarRequest(String vin, UpdateCarRequest updateCarRequest) {
        Car foundedCar = carRepository.findCarByVIN(vin).orElse(null);
        if (foundedCar == null) return null;

        foundedCar.setVIN(updateCarRequest.getVin());
        foundedCar.setModel(updateCarRequest.getModel());
        foundedCar.setStatus(updateCarRequest.getStatus());
        foundedCar.setLicensePlateNumber(foundedCar.getLicensePlateNumber());

        log.info("Car information with VIN: {} was updated",  foundedCar.getVIN());

        return carRepository.save(foundedCar);
    }

    @Override
    public Car updateCarStatus(String vin, CarStatus status) {
        Car foundedCar = carRepository.findCarByVIN(vin).orElse(null);
        if (foundedCar == null) return null;

        foundedCar.setStatus(status);
        log.info("CarStatus with VIN: {} was updated to {}", foundedCar.getVIN(), foundedCar.getStatus());
        return carRepository.save(foundedCar);
    }

    @Override
    @Transactional
    public void deleteCar(String vin) {
        carRepository.deleteCarByVIN(vin);
        log.info("Car with VIN: {} was deleted", vin);
    }
}
