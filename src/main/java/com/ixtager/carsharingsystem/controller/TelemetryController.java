package com.ixtager.carsharingsystem.controller;

import com.ixtager.carsharingsystem.model.Car;
import com.ixtager.carsharingsystem.request.TelemetryUpdateRequest;
import com.ixtager.carsharingsystem.service.car.CarServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/telemetry")
public class TelemetryController {
    private final CarServiceImpl carService;

    @PutMapping("/update_telemetry")
    public ResponseEntity<Car> updateTelemetry(String vin, @RequestBody TelemetryUpdateRequest telemetryUpdateRequest) {
        Car car = carService.updateCarByUpdateTelemetryRequest(vin, telemetryUpdateRequest);
        if (car == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

}
