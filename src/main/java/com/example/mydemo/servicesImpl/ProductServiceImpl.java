package com.example.mydemo.servicesImpl;

import com.example.mydemo.entities.Product;
import com.example.mydemo.exceptions.ProductNotFoundException;
import com.example.mydemo.repositories.ProductRepository;
import com.example.mydemo.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product getProductById(UUID id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product, UUID id) {
        Product existingProduct = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        existingProduct.setId(id);
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(UUID id) {
        Product existingProduct = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        productRepository.delete(existingProduct);
    }

    @Override
    public List<Product> getProductByCategory(String name) {
        return productRepository.findByCategory_Name(name);
    }

    @Override
    public List<Product> getProductByName(String name) {
        return productRepository.findByNameContains(name);
    }

    @Override
    public List<Product> getProductByPriceBetween(Double min, Double max) {
        return productRepository.findByPriceBetween(min, max);
    }

    @Override
    public List<Product> getProductByQuantity(Integer qte) {
        return productRepository.findByQuantityEquals(qte);
    }
}
