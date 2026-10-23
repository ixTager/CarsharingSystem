package com.ixtager.carsharingsystem.service.maintenanceLog;

import com.ixtager.carsharingsystem.jpa.MaintenanceLogRepository;
import com.ixtager.carsharingsystem.model.MaintenanceLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceLogServiceImpl implements MaintenanceLogService {
    private final MaintenanceLogRepository maintenanceLogRepository;

    @Override
    public List<MaintenanceLog> getAllByCar_Vin(String vin) {
        return maintenanceLogRepository.findAllByCarVIN(vin);
    }
}
