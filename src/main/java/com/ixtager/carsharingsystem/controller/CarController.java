package com.ixtager.carsharingsystem.controller;

import com.ixtager.carsharingsystem.model.Car;
import com.ixtager.carsharingsystem.service.car.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cars")
public class CarController {
    private final CarService carService;

    @GetMapping
    public List<Car> getAllCars() {
        return null;
    }

    @PostMapping("/add_car")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        Car newCar = carService.addCar(car);
        return ResponseEntity.ok(newCar);
    }


}
