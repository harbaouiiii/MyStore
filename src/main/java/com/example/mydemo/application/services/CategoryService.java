package com.example.mydemo.application.services;

import com.example.mydemo.application.dtos.CategoryDTO;
import com.example.mydemo.persistance.entities.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CategoryDTO getCategoryById(UUID id);
    List<CategoryDTO> getAllCategories();
    CategoryDTO addCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(CategoryDTO categoryDTO, UUID id);
    void deleteCategory(UUID id);
}
