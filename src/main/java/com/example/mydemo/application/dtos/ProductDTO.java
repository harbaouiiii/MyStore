package com.example.mydemo.application.dtos;

import com.example.mydemo.persistance.entities.Category;
import com.example.mydemo.persistance.entities.Image;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDTO {
    private UUID id;
    @NotBlank(message = "Product name is required")
    private String name;
    private String description;
    @NotNull(message = "Product price is required")
    @Min(0)
    private Double price;
    private Category category;
    private Set<Image> images;
}
