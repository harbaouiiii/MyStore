package com.example.mydemo.services;

import com.example.mydemo.entities.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    public ResponseEntity<Category> getCategoryById(UUID id);
    public List<Category> getAllCategories();
    public ResponseEntity<Category> addCategory(Category category);
    public ResponseEntity<Category> updateCategory(Category category, UUID id);
    public ResponseEntity<Void> deleteCategory(UUID id);
}
