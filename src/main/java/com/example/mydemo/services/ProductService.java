package com.example.mydemo.services;

import com.example.mydemo.entities.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product getProductById(UUID id);
    List<Product> getAllProducts();
    Product addProduct(Product product);
    Product updateProduct(Product product, UUID id);
    void deleteProduct(UUID id);
    List<Product> getProductByCategory(String name);
    List<Product> getProductByName(String name);
    List<Product> getProductByPriceBetween(Double min, Double max);
    List<Product> getProductByQuantity(Integer qte);
}
