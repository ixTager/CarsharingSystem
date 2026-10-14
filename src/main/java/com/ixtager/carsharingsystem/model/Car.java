package com.ixtager.carsharingsystem.model;

import com.ixtager.carsharingsystem.enumerating.CarStatus;
import jakarta.persistence.*;
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
@Table(name = "cats")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vin", unique = true, nullable = false)
    private String VIN;

    @Column(name = "plate_number", unique = true, nullable = false)
    private String licensePlateNumber;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CarStatus status;

    @Column(name = "mileage",  nullable = false)
    @Builder.Default
    private Long mileage = 0L;

    @Column(name = "fuel_level", nullable = false)
    private Long fuelLevel;

    @Column(name = "last_latitude")
    private BigDecimal lastLatitude;

    @Column(name = "last_longitude")
    private BigDecimal lastLongitude;

    @Column(name = "tasks")
    @OneToMany(mappedBy = "car")
    @Builder.Default
    private List<Task> tasks = new ArrayList<>();

    @OneToOne(mappedBy = "car")
    private MaintenanceLog lastMaintenanceLog;

}
