package com.example.mydemo.application.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryDTO {
    private UUID id;
    @NotBlank(message = "Category name is required")
    private String name;
}
