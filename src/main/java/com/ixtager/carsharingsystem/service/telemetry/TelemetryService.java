package com.ixtager.carsharingsystem.service.telemetry;

import com.ixtager.carsharingsystem.model.Car;
import com.ixtager.carsharingsystem.request.TelemetryUpdateRequest;

public interface TelemetryService {
    Car updateCarTelemetry(TelemetryUpdateRequest telemetryUpdateRequest);
}
