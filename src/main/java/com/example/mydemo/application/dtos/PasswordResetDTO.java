package com.example.mydemo.application.dtos;

import lombok.Data;

@Data
public class PasswordResetDTO {
    private String newPassword;
    private String confirmPassword;
}
