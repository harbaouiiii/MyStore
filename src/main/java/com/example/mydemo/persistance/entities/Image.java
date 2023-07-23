package com.example.mydemo.persistance.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;
    private String name;
    private String type;
    @Lob
    @Column(length = 1000)
    private byte[] imageData;
    @ManyToMany(mappedBy = "images")
    private Set<Product> products;
}
