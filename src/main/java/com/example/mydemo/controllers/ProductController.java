package com.example.mydemo.controllers;

import com.example.mydemo.entities.Product;
import com.example.mydemo.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID id){
        return productService.getProductById(id);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product){
        return productService.addProduct(product);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product, @PathVariable UUID id){
        return productService.updateProduct(product,id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id){
        return productService.deleteProduct(id);
    }

}
