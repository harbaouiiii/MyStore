package com.example.mydemo.application.mappers;

import com.example.mydemo.application.dtos.ProductDTO;
import com.example.mydemo.persistance.entities.Product;
import com.example.mydemo.persistance.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductMapperImpl implements ProductMapper {
    private final ProductRepository productRepository;
    @Override
    public ProductDTO toProductDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory(),
                product.getImages()
                );
    }

    @Override
    public Product toProduct(ProductDTO productDTO) {
        return new Product(
                productDTO.getId(),
                productDTO.getName(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                productRepository.findById(productDTO.getId()).get().getQuantity(),
                productDTO.getCategory(),
                productDTO.getImages()
        );
    }

    @Override
    public List<ProductDTO> toProductDTO(List<Product> products) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : products) {
            productDTOS.add(toProductDTO(product));
        }
        return productDTOS;
    }
}
