package com.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

    @Schema(description = "Уникальный идентификатор пользователя", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Size(max = 100, message = "Имя пользователя не должно превышать 100 символов")
    @Schema(description = "Имя пользователя", example = "Иван Иванов", required = true)
    private String name;

    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Некорректный формат email")
    @Schema(description = "Email пользователя", example = "ivan@example.com", required = true)
    private String email;
}
