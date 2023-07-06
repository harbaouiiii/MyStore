package com.example.mydemo.application.mappers;

import com.example.mydemo.application.dtos.CategoryDTO;
import com.example.mydemo.persistance.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {}
)
public interface CategoryMapper {
    CategoryDTO toCategoryDTO(Category category);
    Category toCategory(CategoryDTO categoryDTO);
    List<CategoryDTO> toCategoryDTO(List<Category> categories);
}
