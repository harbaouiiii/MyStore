package com.example.mydemo.application.servicesImpl;

import com.example.mydemo.application.dtos.CategoryDTO;
import com.example.mydemo.application.mappers.CategoryMapper;
import com.example.mydemo.persistance.entities.Category;
import com.example.mydemo.application.exceptions.CategoryNotFoundException;
import com.example.mydemo.persistance.repositories.CategoryRepository;
import com.example.mydemo.application.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDTO getCategoryById(UUID id) {
        return categoryMapper.toCategoryDTO(categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new));
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryMapper.toCategoryDTO(categoryRepository.findAll());
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        return categoryMapper.toCategoryDTO(categoryRepository.save(categoryMapper.toCategory(categoryDTO)));
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, UUID id) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        existingCategory.setId(id);
        return categoryMapper.toCategoryDTO(categoryRepository.save(existingCategory));
    }

    @Override
    public void deleteCategory(UUID id) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        categoryRepository.delete(existingCategory);
    }
}
