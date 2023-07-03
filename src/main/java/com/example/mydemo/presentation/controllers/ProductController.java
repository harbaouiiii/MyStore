package com.example.mydemo.presentation.controllers;

import com.example.mydemo.persistance.entities.Product;
import com.example.mydemo.application.exceptions.ProductNotFoundException;
import com.example.mydemo.application.services.ProductService;
import com.example.mydemo.utils.Roles;
import com.example.mydemo.utils.Summary;
import com.example.mydemo.utils.Tags;
import com.example.mydemo.utils.Urls;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping(Urls.BASE_PRODUCT_URL)
@CrossOrigin(origins = Urls.ORIGINS_URL)
@Tag(name = Tags.PRODUCT)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = Summary.GET_ALL_PRODUCTS)
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @Operation(summary = Summary.GET_PRODUCT_BY_ID)
    @GetMapping(value = Urls.PRODUCT_ID_PARAM)
    public ResponseEntity<Product> getProductById(@PathVariable UUID id){
        try{
            return ResponseEntity.ok(productService.getProductById(id));
        } catch (ProductNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = Summary.ADD_PRODUCT)
    @PostMapping
    @PreAuthorize(Roles.HAS_ROLE_ADMIN_OR_MOD)
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(product));
    }

    @Operation(summary = Summary.UPDATE_PRODUCT)
    @PutMapping(value = Urls.PRODUCT_ID_PARAM)
    @PreAuthorize(Roles.HAS_ROLE_ADMIN_OR_MOD)
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product, @PathVariable UUID id){
        try{
            return ResponseEntity.ok(productService.updateProduct(product, id));
        } catch (ProductNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = Summary.DELETE_PRODUCT)
    @DeleteMapping(value = Urls.PRODUCT_ID_PARAM)
    @PreAuthorize(Roles.HAS_ROLE_ADMIN)
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

    @Operation(summary = Summary.GET_PRODUCT_BY_CATEGORY)
    @GetMapping(value = Urls.PRODUCT_CATEGORY_NAME_PARAM)
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable String name){
        return ResponseEntity.ok(productService.getProductByCategory(name));
    }

    @Operation(summary = Summary.GET_PRODUCT_BY_NAME)
    @GetMapping(value = Urls.PRODUCT_NAME_PARAM)
    public ResponseEntity<List<Product>> getProductByName(@PathVariable String name){
        return ResponseEntity.ok(productService.getProductByName(name));
    }

    @Operation(summary = Summary.GET_PRODUCT_BY_PRICE_BETWEEN)
    @GetMapping(value = Urls.PRODUCT_PRICE_MIN_MAX_PARAM)
    public ResponseEntity<List<Product>> getProductByPriceBetween(@PathVariable Double min, @PathVariable Double max){
        return ResponseEntity.ok(productService.getProductByPriceBetween(min, max));
    }

    @Operation(summary = Summary.GET_PRODUCT_BY_QUANTITY)
    @GetMapping(value = Urls.PRODUCT_QUANTITY_PARAM)
    public ResponseEntity<List<Product>> getProductByQuantity(@PathVariable Integer qte){
        return ResponseEntity.ok(productService.getProductByQuantity(qte));
    }

}
