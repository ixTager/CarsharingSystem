package com.ixtager.carsharingsystem.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TelemetryUpdateRequest {
    private String vin;
    private long currentMileage;
    private long fuelLevel;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
