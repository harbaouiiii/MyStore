package com.example.mydemo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;
    @NotBlank(message = "Product name is required")
    @Size(max = 50)
    private String name;
    private String description;
    @NotNull(message = "Product price is required")
    @Min(0)
    private Double price;
    @NotNull(message = "Product quantity is required")
    @Min(0)
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}