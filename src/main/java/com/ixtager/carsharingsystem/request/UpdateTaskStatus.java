package com.ixtager.carsharingsystem.request;

import com.ixtager.carsharingsystem.enumerating.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskStatus {
    @Size(min = 17, max = 17)
    private String vin;

    @NotBlank(message = "Task status must be not blank")
    private TaskStatus status;
}
