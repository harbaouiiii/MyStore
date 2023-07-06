package com.example.mydemo.application.mappers;

import com.example.mydemo.application.dtos.CategoryDTO;
import com.example.mydemo.persistance.entities.Category;
import com.example.mydemo.persistance.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryMapperImpl implements CategoryMapper{
    private final ProductRepository productRepository;

    @Override
    public CategoryDTO toCategoryDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getName());
    }

    @Override
    public Category toCategory(CategoryDTO categoryDTO) {
        return new Category(categoryDTO.getId(), categoryDTO.getName(),productRepository.findByCategory_Name(categoryDTO.getName()));
    }

    @Override
    public List<CategoryDTO> toCategoryDTO(List<Category> categories) {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category category : categories) {
            categoryDTOS.add(toCategoryDTO(category));
        }
        return categoryDTOS;
    }
}
