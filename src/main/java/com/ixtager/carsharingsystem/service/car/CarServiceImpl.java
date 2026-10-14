package com.ixtager.carsharingsystem.service.car;

import com.ixtager.carsharingsystem.enumerating.CarStatus;
import com.ixtager.carsharingsystem.jpa.CarRepository;
import com.ixtager.carsharingsystem.model.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

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
    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Long id, Car car) {
        Car foundedCar = carRepository.findById(id).orElse(null);
        if (foundedCar == null) return carRepository.save(car);
        foundedCar.setModel(car.getModel());
        foundedCar.setStatus(car.getStatus());

        return carRepository.save(foundedCar);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
