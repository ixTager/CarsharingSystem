package com.ixtager.carsharingsystem.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCarRequest {
    private String VIN;
    private String licensePlateNumber;
    private String model;
}
