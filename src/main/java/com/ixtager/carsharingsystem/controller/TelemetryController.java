package com.ixtager.carsharingsystem.controller;

import com.ixtager.carsharingsystem.model.Car;
import com.ixtager.carsharingsystem.request.TelemetryUpdateRequest;
import com.ixtager.carsharingsystem.service.car.CarServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/telemetry")
public class TelemetryController {
    private final CarServiceImpl carService;

    @GetMapping
    public ResponseEntity<Car> updateTelemetry(@RequestBody TelemetryUpdateRequest telemetryUpdateRequest) {
        Car car = carService.updateCarByUpdateTelemetryRequest(telemetryUpdateRequest);
        if (car == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

}
