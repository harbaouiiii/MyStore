package com.example.mydemo.services;

import com.example.mydemo.entities.Category;
import com.example.mydemo.entities.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ResponseEntity<Product> getProductById(UUID id);
    List<Product> getAllProducts();
    ResponseEntity<Product> addProduct(Product product);
    ResponseEntity<Product> updateProduct(Product product, UUID id);
    ResponseEntity<Void> deleteProduct(UUID id);
    ResponseEntity<List<Product>> getProductByCategory(String name);
    ResponseEntity<List<Product>> getProductByName(String name);
    ResponseEntity<List<Product>> getProductByPriceBetween(Double min, Double max);
    ResponseEntity<List<Product>> getProductByQuantity(Integer qte);
}
