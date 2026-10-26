package com.ixtager.carsharingsystem.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    @Size(min = 5, max= 50, message = "Имя пользователя содержить от 5 до 50 символов")
    @NotBlank(message = "Имя пользователя не должно быть пустым")
    private String username;

    @Size(min = 5, max = 255, message = "Адрес электронной почты должен содержать от 5 до 255 символов")
    @NotBlank(message = "Адресс электронной почты не должен быть пустым")
    private String email;

    @Size(max = 255, message = "Длина пароля не может быть больше 255 символов")
    private String password;
}
