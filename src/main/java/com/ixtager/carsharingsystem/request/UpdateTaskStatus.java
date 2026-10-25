package com.ixtager.carsharingsystem.request;

import com.ixtager.carsharingsystem.enumerating.TaskStatus;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskStatus {
    private String vin;
    private TaskStatus status;
}
