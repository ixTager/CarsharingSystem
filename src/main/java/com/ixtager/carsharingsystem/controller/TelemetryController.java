package com.ixtager.carsharingsystem.controller;

import com.ixtager.carsharingsystem.model.Car;
import com.ixtager.carsharingsystem.request.TelemetryUpdateRequest;
import com.ixtager.carsharingsystem.service.telemetry.TelemetryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/telemetry")
@Tag(name = "Telemetry controller")
public class TelemetryController {
    private final TelemetryServiceImpl telemetryService;

    @PostMapping("/update_telemetry")
    @Operation(
            summary = "Updating Car Telemetry",
            description = "Return updated car by TelemetryUpdateRequest"
    )
    @ApiResponse(responseCode = "200" , description = "Updating Telemetry is successful")
    @ApiResponse(responseCode = "404", description = "Task was not found")
    public ResponseEntity<Car> updateTelemetry(@Valid @RequestBody TelemetryUpdateRequest telemetryUpdateRequest) {
        Car car = telemetryService.updateCarTelemetry(telemetryUpdateRequest);
        if (car == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

}
