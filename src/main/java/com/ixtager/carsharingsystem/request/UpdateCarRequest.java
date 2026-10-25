package com.ixtager.carsharingsystem.request;

import com.ixtager.carsharingsystem.enumerating.CarStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCarRequest {
    private String vin;
    private String model;
    private CarStatus status;
    private String licensePlate;
}
