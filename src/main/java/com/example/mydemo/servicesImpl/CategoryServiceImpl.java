package com.example.mydemo.servicesImpl;

import com.example.mydemo.entities.Category;
import com.example.mydemo.exceptions.CategoryNotFoundException;
import com.example.mydemo.repositories.CategoryRepository;
import com.example.mydemo.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category, UUID id) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        existingCategory.setId(id);
        return categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteCategory(UUID id) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        categoryRepository.delete(existingCategory);
    }
}
