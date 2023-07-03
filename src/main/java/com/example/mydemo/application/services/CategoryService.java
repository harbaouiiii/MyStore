package com.example.mydemo.application.services;

import com.example.mydemo.persistance.entities.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    Category getCategoryById(UUID id);
    List<Category> getAllCategories();
    Category addCategory(Category category);
    Category updateCategory(Category category, UUID id);
    void deleteCategory(UUID id);
}
