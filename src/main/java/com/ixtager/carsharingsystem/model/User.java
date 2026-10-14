package com.ixtager.carsharingsystem.model;

import com.ixtager.carsharingsystem.enumerating.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "creatorLog")
    @Builder.Default
    private List<MaintenanceLog> maintenanceLogs = new ArrayList<>();

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
