package com.ironman.restaurantmanagement.expose.controller;

import com.ironman.restaurantmanagement.presistence.entity.Category;
import com.ironman.restaurantmanagement.presistence.repository.CategoryRepository;
import com.ironman.restaurantmanagement.shared.state.enums.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryRepository categoryRepository;

    Long id = 1L;
    Category category;

    @BeforeEach
    void setUp() {
        category = Category.builder()
                .id(id)
                .name("Pizza")
                .description("Pizza description")
                .urlKey("pizza")
                .state(State.ENABLED.getValue())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Test
    void canFindAllCategories() throws Exception {
        // Given
        Category category2 = Category.builder()
                .id(2L)
                .name("Burger")
                .description("Burger description")
                .urlKey("burger")
                .state(State.ENABLED.getValue())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(categoryRepository.findAll())
                .thenReturn(List.of(category, category2));

        // When
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/categories");

        ResultActions result = mockMvc.perform(request);

        // Then
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].id").value(id))
                .andExpect(jsonPath("$[0].name").value(category.getName()))
                .andExpect(jsonPath("$.length()").value(2));

    }



    @Test
    void canFindCategoryById() throws Exception {
        // Given
        when(categoryRepository.findById(anyLong()))
                .thenReturn(Optional.of(category));

        // When
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/categories/"+ id);

        ResultActions result = mockMvc.perform(request);

        // Then

        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(category.getName()))
                .andExpect(jsonPath("$.description").value(category.getDescription()))
                .andExpect(jsonPath("$.urlKey").value(category.getUrlKey()))
                .andExpect(jsonPath("$.state.value").value(State.ENABLED.getValue()))
                .andExpect(jsonPath("$.state.name").value(State.ENABLED.getName()))
                .andExpect(jsonPath("$.state.enabled").value(State.ENABLED.isEnabled()))
                .andExpect(jsonPath("$.createdAt").exists());

    }

}