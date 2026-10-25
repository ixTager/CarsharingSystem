package com.ixtager.carsharingsystem.controller;

import com.ixtager.carsharingsystem.model.Car;
import com.ixtager.carsharingsystem.request.TelemetryUpdateRequest;
import com.ixtager.carsharingsystem.service.telemetry.TelemetryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/telemetry")
public class TelemetryController {
    private final TelemetryServiceImpl telemetryService;

    @PostMapping("/update_telemetry")
    public ResponseEntity<Car> updateTelemetry(@RequestBody TelemetryUpdateRequest telemetryUpdateRequest) {
        Car car = telemetryService.updateCarTelemetry(telemetryUpdateRequest);
        if (car == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

}
