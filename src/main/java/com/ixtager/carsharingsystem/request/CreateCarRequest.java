package com.ixtager.carsharingsystem.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCarRequest {
    @Size(min = 17, max = 17)
    @NotBlank(message = "Vin must be not blank")
    @NotNull(message = "Vin must be not null")
    private String VIN;

    @Size(min = 1, max = 12)
    @NotBlank(message = "License plate must be not blank")
    @NotNull(message = "License plage must be not null")
    private String licensePlateNumber;

    @Size(min = 1, max = 255)
    private String model;
}
