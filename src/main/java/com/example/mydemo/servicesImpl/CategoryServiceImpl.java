package com.example.mydemo.servicesImpl;

import com.example.mydemo.entities.Category;
import com.example.mydemo.repositories.CategoryRepository;
import com.example.mydemo.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl (CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseEntity<Category> getCategoryById(UUID id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public ResponseEntity<Category> addCategory(Category category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryRepository.save(category));
    }

    @Override
    public ResponseEntity<Category> updateCategory(Category category, UUID id) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if(existingCategory.isPresent()) {
            category.setId(id);
            return ResponseEntity.ok(categoryRepository.save(category));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> deleteCategory(UUID id) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if(existingCategory.isPresent()) {
            categoryRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
