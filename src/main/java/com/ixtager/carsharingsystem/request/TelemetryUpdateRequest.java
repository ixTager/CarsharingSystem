package com.ixtager.carsharingsystem.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TelemetryUpdateRequest {
    @Size(min = 17, max = 17)
    @NotBlank(message = "Vin must be not blank")
    private String vin;

    @NotBlank(message = "Current mileage must be not blank")
    private long currentMileage;

    @NotBlank(message = "Fuel level must be not blank")
    private long fuelLevel;

    @NotBlank(message = "Latitude must be not blank")
    @NotNull(message = "Latitude must be not null")
    private BigDecimal latitude;

    @NotBlank(message = "Longitude must be not blank")
    @NotNull(message = "Longitude must be not null")
    private BigDecimal longitude;
}
