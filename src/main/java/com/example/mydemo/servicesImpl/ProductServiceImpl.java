package com.example.mydemo.servicesImpl;

import com.example.mydemo.entities.Product;
import com.example.mydemo.repositories.ProductRepository;
import com.example.mydemo.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Product> getProductById(UUID id) {
        Optional<Product> existingProduct = productRepository.findById(id);
        return existingProduct.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ResponseEntity<Product> addProduct(Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(product));
    }

    @Override
    public ResponseEntity<Product> updateProduct(Product product, UUID id) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if(existingProduct.isPresent()) {
            product.setId(id);
            return ResponseEntity.ok(productRepository.save(product));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> deleteProduct(UUID id) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if(existingProduct.isPresent()){
            productRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
