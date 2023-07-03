package com.example.mydemo.controllers;

import com.example.mydemo.entities.Category;
import com.example.mydemo.exceptions.CategoryNotFoundException;
import com.example.mydemo.services.CategoryService;
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
@RequestMapping("/category")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "Get all categories")
    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @Operation(summary = "Get category by id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable UUID id){
        try {
            return ResponseEntity.ok(categoryService.getCategoryById(id));
        } catch (CategoryNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Add category")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MOD')")
    public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.addCategory(category));
    }

    @Operation(summary = "Update category")
    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MOD')")
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category ,@PathVariable UUID id){
        try {
            return ResponseEntity.ok(categoryService.updateCategory(category, id));
        } catch (CategoryNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete category")
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String,Boolean>> deleteCategory(@PathVariable UUID id){
        Map<String,Boolean> response = new HashMap<>();
        response.put("Category "+ categoryService.getCategoryById(id).getName() +" deleted", Boolean.TRUE);
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok(response);
        } catch (CategoryNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

}
