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

    @GetMapping(value = "/category/{name}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable String name){
        return productService.getProductByCategory(name);
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<List<Product>> getProductByName(@PathVariable String name){
        return productService.getProductByName(name);
    }

    @GetMapping(value = "/price/{min}/{max}")
    public ResponseEntity<List<Product>> getProductByPriceBetween(@PathVariable Double min, @PathVariable Double max){
        return productService.getProductByPriceBetween(min,max);
    }

    @GetMapping(value = "/quantity/{qte}")
    public ResponseEntity<List<Product>> getProductByQuantity(@PathVariable Integer qte){
        return productService.getProductByQuantity(qte);
    }

}
