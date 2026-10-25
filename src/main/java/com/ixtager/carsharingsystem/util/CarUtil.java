package com.ixtager.carsharingsystem.util;

import com.ixtager.carsharingsystem.enumerating.CarStatus;
import com.ixtager.carsharingsystem.model.Car;
import com.ixtager.carsharingsystem.request.CreateCarRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CarUtil {

    public Car createCarEntityByCarRequest(CreateCarRequest createCarRequest) {
        return Car.builder()
                .VIN(createCarRequest.getVIN())
                .licensePlateNumber(createCarRequest.getLicensePlateNumber())
                .model(createCarRequest.getModel())
                .status(CarStatus.AVAILABLE)
                .mileage(0L)
                .fuelLevel(0L)
                .lastLatitude(new BigDecimal(0L))
                .lastLongitude(new BigDecimal(0L))
                .build();
    }
}
