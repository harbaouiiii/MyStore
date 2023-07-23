package com.example.mydemo.application.mappers;

import com.example.mydemo.application.dtos.ProductDTO;
import com.example.mydemo.persistance.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {}
)
public interface ProductMapper {
    ProductDTO toProductDTO(Product product);
    Product toProduct(ProductDTO productDTO);
    List<ProductDTO> toProductDTO(List<Product> products);
}
