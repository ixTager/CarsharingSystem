package com.ixtager.carsharingsystem.controller;

import com.ixtager.carsharingsystem.model.MaintenanceLog;
import com.ixtager.carsharingsystem.service.maintenanceLog.MaintenanceLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/logs")
@Tag(name = "Maintenance log controller")
public class MaintenanceLogController {
    private final MaintenanceLogService maintenanceLogService;

    @GetMapping("/{vin}")
    @PreAuthorize("hasAnyRole('DISPATCHER','MECHANIC')")
    @Operation(
            summary = "Getting maintenance logs by vin",
            description = "Return list of maintenance logs by vin"
    )
    @ApiResponse(responseCode = "200", description = "Getting maintenance logs is successful")
    public ResponseEntity<List<MaintenanceLog>> getMaintenanceLogsByVin(@PathVariable String vin) {
        return ResponseEntity.ok(maintenanceLogService.getAllByCar_Vin(vin));
    }
}
