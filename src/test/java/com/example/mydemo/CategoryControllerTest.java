package com.example.mydemo;

import com.example.mydemo.controllers.CategoryController;
import com.example.mydemo.entities.Category;
import com.example.mydemo.services.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @MockBean
    private CategoryService categoryService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllCategories_ReturnListOfCategories() throws Exception {
        Category category = new Category(UUID.fromString("54e4c4a5-3ec0-4a38-9b30-890a6ef09975"),"SmartPhones");
        Category category1 = new Category(UUID.fromString("e28783dc-88b9-4eb6-b3e1-1b22000224bd"),"Laptops");

        when(categoryService.getAllCategories()).thenReturn(Arrays.asList(category, category1));

        mockMvc.perform(MockMvcRequestBuilders.get("/category"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value("54e4c4a5-3ec0-4a38-9b30-890a6ef09975"))
                .andExpect(jsonPath("$[0].name").value("SmartPhones"))
                .andExpect(jsonPath("$[1].id").value("e28783dc-88b9-4eb6-b3e1-1b22000224bd"))
                .andExpect(jsonPath("$[1].name").value("Laptops"));
    }

}
