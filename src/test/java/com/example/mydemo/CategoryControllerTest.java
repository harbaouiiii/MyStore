package com.example.mydemo;

import com.example.mydemo.controllers.CategoryController;
import com.example.mydemo.entities.Category;
import com.example.mydemo.services.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    CategoryService categoryService;
    @InjectMocks
    CategoryController categoryController;

    @Test
    void should_GetCategoryById_Success(){
        /*Category expectedCategory = new Category(UUID.randomUUID(),"Smartphones");
        when(categoryService.getCategoryById(expectedCategory.getId())).thenReturn(ResponseEntity.ok(expectedCategory));

        ResponseEntity<Category> actualCategory = categoryController.getCategoryById(expectedCategory.getId());

        assertEquals(HttpStatus.OK, actualCategory.getStatusCode());
        assertNotNull(actualCategory);
        assertEquals(expectedCategory.getId(), Objects.requireNonNull(actualCategory.getBody()).getId());
        assertEquals(expectedCategory.getName(),actualCategory.getBody().getName());*/
    }

    @Test
    void should_GetCategoryById_NotFound(){
        /*Category expectedCategory = new Category(UUID.randomUUID(),"Smartphones");
        when(categoryService.getCategoryById(expectedCategory.getId())).thenReturn(ResponseEntity.notFound().build());

        ResponseEntity<Category> actualCategory = categoryController.getCategoryById(expectedCategory.getId());

        assertEquals(HttpStatus.NOT_FOUND, actualCategory.getStatusCode());
        assertThrows(NullPointerException.class, () -> actualCategory.getBody().getId());*/
    }

    @Test
    void should_AddCategory_Success(){
        /*when(categoryService.addCategory(any(Category.class))).thenReturn(ResponseEntity.status(HttpStatus.CREATED).build());
        Category expectedCategory = new Category(UUID.randomUUID(),"Smartphones");

        ResponseEntity<Category> actualCategory = categoryController.addCategory(expectedCategory);

        assertEquals(HttpStatus.CREATED, actualCategory.getStatusCode());
        assertNotNull(actualCategory);
        assertEquals(expectedCategory.getId(), Objects.requireNonNull(actualCategory.getBody()).getId());
        assertEquals(expectedCategory.getName(),actualCategory.getBody().getName());*/

    }

}
