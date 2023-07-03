package com.example.mydemo.presentation.controllers;

import com.example.mydemo.persistance.entities.Category;
import com.example.mydemo.application.exceptions.CategoryNotFoundException;
import com.example.mydemo.application.services.CategoryService;
import com.example.mydemo.utils.Roles;
import com.example.mydemo.utils.Summary;
import com.example.mydemo.utils.Tags;
import com.example.mydemo.utils.Urls;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(Urls.BASE_CATEGORY_URL)
@CrossOrigin(origins = Urls.ORIGINS_URL)
@Tag(name = Tags.CATEGORY)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = Summary.GET_ALL_CATEGORIES)
    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @Operation(summary = Summary.GET_CATEGORY_BY_ID)
    @GetMapping(value = Urls.CATEGORY_ID_PARAM)
    public ResponseEntity<Category> getCategoryById(@PathVariable UUID id){
        try {
            return ResponseEntity.ok(categoryService.getCategoryById(id));
        } catch (CategoryNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = Summary.ADD_CATEGORY)
    @PostMapping
    @PreAuthorize(Roles.HAS_ROLE_ADMIN_OR_MOD)
    public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.addCategory(category));
    }

    @Operation(summary = Summary.UPDATE_CATEGORY)
    @PutMapping(value = Urls.CATEGORY_ID_PARAM)
    @PreAuthorize(Roles.HAS_ROLE_ADMIN_OR_MOD)
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category ,@PathVariable UUID id){
        try {
            return ResponseEntity.ok(categoryService.updateCategory(category, id));
        } catch (CategoryNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = Summary.DELETE_CATEGORY)
    @DeleteMapping(value = Urls.CATEGORY_ID_PARAM)
    @PreAuthorize(Roles.HAS_ROLE_ADMIN)
    public ResponseEntity<Map<String,Boolean>> deleteCategory(@PathVariable UUID id){
        Map<String,Boolean> response = new HashMap<>();
        response.put("Category "+ categoryService.getCategoryById(id).getName() +" deleted", Boolean.TRUE);
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok(response);
        } catch (CategoryNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

}
