package com.ixtager.carsharingsystem.controller;

import com.ixtager.carsharingsystem.enumerating.CarStatus;
import com.ixtager.carsharingsystem.model.Car;
import com.ixtager.carsharingsystem.request.CreateCarRequest;
import com.ixtager.carsharingsystem.request.UpdateCarRequest;
import com.ixtager.carsharingsystem.service.car.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cars")
public class CarController {
    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars(
            @RequestParam(required = false) String byStatus) {
        //Todo implementation findCars by filters
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Car car = carService.getCarById(id);
        if (car == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(car);
    }

    @PostMapping("/add_car")
    public ResponseEntity<Car> addCar(@RequestBody CreateCarRequest createCarRequest) {
        Car newCar = carService.addNewCar(createCarRequest);
        return ResponseEntity.ok(newCar);
    }

    @PutMapping("/{vin}")
    public ResponseEntity<Car> updateCar(String vin, @RequestBody UpdateCarRequest updateCarRequest) {
        Car updatedCar = carService.updateCarByUpdateCarRequest(vin, updateCarRequest);
        if (updatedCar == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updatedCar);
    }

    @PutMapping("/change_status/{vin}/{status}")
    public ResponseEntity<Car> changeStatus(@PathVariable String vin, CarStatus status) {
        Car updatedCar = carService.updateCarStatus(vin ,status);
        if (updatedCar == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(updatedCar);
    }

}
