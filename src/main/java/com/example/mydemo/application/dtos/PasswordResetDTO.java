package com.example.mydemo.application.dtos;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PasswordResetDTO {
    @Size(min = 8)
    private String newPassword;
    @Size(min = 8)
    private String confirmPassword;
}
