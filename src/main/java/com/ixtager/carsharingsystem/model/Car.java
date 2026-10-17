package com.ixtager.carsharingsystem.model;

import com.ixtager.carsharingsystem.enumerating.CarStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 17, max = 17)
    @Column(name = "vin", unique = true, nullable = false)
    private String VIN;

    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "plate_number", unique = true, nullable = false)
    private String licensePlateNumber;

    @NotNull
    @Column(name = "model", nullable = false)
    private String model;

    @NotNull
    @NotNull
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CarStatus status;

    @NotNull
    @Min(0L)
    @Column(name = "mileage",  nullable = false)
    @Builder.Default
    private Long mileage = 0L;

    @NotNull
    @Column(name = "fuel_level", nullable = false)
    private Long fuelLevel;

    @Column(name = "last_latitude")
    private BigDecimal lastLatitude;

    @Column(name = "last_longitude")
    private BigDecimal lastLongitude;

    @OneToMany(mappedBy = "car")
    @Builder.Default
    private List<Task> tasks = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "last_maintenance_log_id")
    private MaintenanceLog lastMaintenanceLog;
}
