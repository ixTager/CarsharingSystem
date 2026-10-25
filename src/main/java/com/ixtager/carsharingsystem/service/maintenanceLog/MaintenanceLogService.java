package com.ixtager.carsharingsystem.service.maintenanceLog;

import com.ixtager.carsharingsystem.model.MaintenanceLog;

import java.util.List;

public interface MaintenanceLogService {
    List<MaintenanceLog> getAllByCar_Vin(String vin);
}
