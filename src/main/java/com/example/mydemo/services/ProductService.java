package com.example.mydemo.services;

import com.example.mydemo.entities.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    public ResponseEntity<Product> getProductById(UUID id);
    public List<Product> getAllProducts();
    public ResponseEntity<Product> addProduct(Product product);
    public ResponseEntity<Product> updateProduct(Product product, UUID id);
    public ResponseEntity<Void> deleteProduct(UUID id);
}
