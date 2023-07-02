package com.example.mydemo.controllers;

import com.example.mydemo.entities.Product;
import com.example.mydemo.exceptions.ProductNotFoundException;
import com.example.mydemo.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Get all products")
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @Operation(summary = "Get product by id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID id){
        try{
            return ResponseEntity.ok(productService.getProductById(id));
        } catch (ProductNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Add product")
    @PostMapping
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(product));
    }

    @Operation(summary = "Update product")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product, @PathVariable UUID id){
        try{
            return ResponseEntity.ok(productService.updateProduct(product, id));
        } catch (ProductNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete product")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id){
        try{
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (ProductNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get product by category")
    @GetMapping(value = "/category/{name}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable String name){
        return ResponseEntity.ok(productService.getProductByCategory(name));
    }

    @Operation(summary = "Get product by name")
    @GetMapping(value = "/{name}")
    public ResponseEntity<List<Product>> getProductByName(@PathVariable String name){
        return ResponseEntity.ok(productService.getProductByName(name));
    }

    @Operation(summary = "Get product by price between")
    @GetMapping(value = "/price/{min}/{max}")
    public ResponseEntity<List<Product>> getProductByPriceBetween(@PathVariable Double min, @PathVariable Double max){
        return ResponseEntity.ok(productService.getProductByPriceBetween(min, max));
    }

    @Operation(summary = "Get product by quantity")
    @GetMapping(value = "/quantity/{qte}")
    public ResponseEntity<List<Product>> getProductByQuantity(@PathVariable Integer qte){
        return ResponseEntity.ok(productService.getProductByQuantity(qte));
    }

}
