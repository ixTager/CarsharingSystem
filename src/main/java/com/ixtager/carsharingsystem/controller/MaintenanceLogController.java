package com.ixtager.carsharingsystem.controller;

import com.ixtager.carsharingsystem.model.MaintenanceLog;
import com.ixtager.carsharingsystem.service.maintenanceLog.MaintenanceLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/logs")
public class MaintenanceLogController {
    private final MaintenanceLogService maintenanceLogService;

    @GetMapping("/{vin}")
    public ResponseEntity<List<MaintenanceLog>> getMaintenanceLogsByVin(@PathVariable String vin) {
        return ResponseEntity.ok(maintenanceLogService.getAllByCar_Vin(vin));
    }
}
