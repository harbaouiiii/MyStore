package com.example.mydemo.controllers;

import com.example.mydemo.entities.Category;
import com.example.mydemo.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable UUID id){
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category ,@PathVariable UUID id){
        return categoryService.updateCategory(category,id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id){
        return categoryService.deleteCategory(id);
    }

}
