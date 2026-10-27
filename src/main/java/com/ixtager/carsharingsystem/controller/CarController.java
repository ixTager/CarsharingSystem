package com.ixtager.carsharingsystem.controller;

import com.ixtager.carsharingsystem.enumerating.CarStatus;
import com.ixtager.carsharingsystem.model.Car;
import com.ixtager.carsharingsystem.request.CreateCarRequest;
import com.ixtager.carsharingsystem.request.UpdateCarRequest;
import com.ixtager.carsharingsystem.service.car.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cars")
@Tag(name = "Car controller")
public class CarController {
    private final CarService carService;

    @GetMapping
    @PreAuthorize("hasAnyRole('DISPATCHER','MECHANIC')")
    @Operation(
            summary = "Get cars by filter",
            description = "Return list of cars by parameters"
    )
    @ApiResponse(responseCode = "200", description = "Getting Car is successful")
    public ResponseEntity<List<Car>> getAllCars(
            @RequestParam(required = false) String byStatus) {
        //Todo implementation findCars by filters
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('DISPATCHER','MECHANIC')")
    @Operation(
            summary = "Get Car By Id",
            description = "Return list of Car by Car ID"
    )
    @ApiResponse(responseCode = "200", description = "Getting car by id is successful")
    @ApiResponse(responseCode = "404", description = "Car was not found")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Car car = carService.getCarById(id);
        if (car == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(car);
    }

    @PostMapping("/add_car")
    @PreAuthorize("hasAuthority('ROLE_DISPATCHER')")
    @Operation(
            summary = "Add new Car",
            description = "Adding new Car by CreateCarRequest"
    )
    @ApiResponse(responseCode = "200", description = "Adding car is successful")
    public ResponseEntity<Car> addCar(@Valid @RequestBody CreateCarRequest createCarRequest) {
        Car newCar = carService.addNewCar(createCarRequest);
        return ResponseEntity.ok(newCar);
    }

    @PutMapping("/{vin}")
    @PreAuthorize("hasAuthority('ROLE_DISPATCHER')")
    @Operation(
            summary = "Update Car",
            description = "Return updating car by CarVin and UpdateCarRequest"
    )
    @ApiResponse(responseCode = "200", description = "Updating car is successful")
    @ApiResponse(responseCode = "404", description = "Car was not found")
    public ResponseEntity<Car> updateCar(String vin, @Valid @RequestBody UpdateCarRequest updateCarRequest) {
        Car updatedCar = carService.updateCarByUpdateCarRequest(vin, updateCarRequest);
        if (updatedCar == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updatedCar);
    }

    @PutMapping("/change_status/{vin}/{status}")
    @PreAuthorize("hasAnyRole('DISPATCHER','MECHANIC')")
    @Operation(
            summary = "Change car status",
            description = "Return new Car entity by CarVin and CarStatus"
    )
    @ApiResponse(responseCode = "200", description = "Change car status is successful")
    @ApiResponse(responseCode = "404", description = "Car was not found")
    public ResponseEntity<Car> changeStatus(@PathVariable String vin, @PathVariable CarStatus status) {
        Car updatedCar = carService.updateCarStatus(vin ,status);
        if (updatedCar == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(updatedCar);
    }

}
