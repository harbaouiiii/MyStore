package com.example.mydemo.controllers;

import com.example.mydemo.entities.Product;
import com.example.mydemo.exceptions.ProductNotFoundException;
import com.example.mydemo.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('MOD')")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(product));
    }

    @Operation(summary = "Update product")
    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MOD')")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product, @PathVariable UUID id){
        try{
            return ResponseEntity.ok(productService.updateProduct(product, id));
        } catch (ProductNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete product")
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String,Boolean>> deleteProduct(@PathVariable UUID id){
        Map<String,Boolean> response = new HashMap<>();
        response.put("Product "+ productService.getProductById(id).getName() +" deleted", Boolean.TRUE);
        try{
            productService.deleteProduct(id);
            return ResponseEntity.ok(response);
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
    @GetMapping(value = "/name/{name}")
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
