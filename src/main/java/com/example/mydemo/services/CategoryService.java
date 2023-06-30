package com.example.mydemo.services;

import com.example.mydemo.entities.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    ResponseEntity<Category> getCategoryById(UUID id);
    List<Category> getAllCategories();
    ResponseEntity<Category> addCategory(Category category);
    ResponseEntity<Category> updateCategory(Category category, UUID id);
    ResponseEntity<Void> deleteCategory(UUID id);
}
