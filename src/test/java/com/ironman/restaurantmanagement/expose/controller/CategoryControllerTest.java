package com.ironman.restaurantmanagement.expose.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironman.restaurantmanagement.application.dto.category.CategoryBodyDto;
import com.ironman.restaurantmanagement.presistence.entity.Category;
import com.ironman.restaurantmanagement.presistence.repository.CategoryRepository;
import com.ironman.restaurantmanagement.shared.state.enums.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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
                .get("/categories/" + id);

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

    @Test
    void canCreateCategory() throws Exception {
        // Given
        when(categoryRepository.save(any(Category.class)))
                .thenAnswer(invocation -> {
                    Category categoryCreated = invocation.getArgument(0);
                    categoryCreated.setId(100L);
                    return categoryCreated;
                });

        CategoryBodyDto categoryBody = CategoryBodyDto.builder()
                .name("Pizza Mozarella")
                .description("Pizza Gourmet")
                .build();

        // When
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryBody));

        ResultActions result = mockMvc.perform(request);

        // Then
        result.andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.state.value").value(State.ENABLED.getValue()))
                .andExpect(jsonPath("$.state.name").value(State.ENABLED.getName()))
                .andExpect(jsonPath("$.state.enabled").value(State.ENABLED.isEnabled()));

    }

    @Test
    void canUpdateCategory() throws Exception {
        // Given
        when(categoryRepository.findById(anyLong()))
                .thenReturn(Optional.of(category));

        when(categoryRepository.save(any(Category.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        CategoryBodyDto categoryBody = CategoryBodyDto.builder()
                .name("Pizza Mozarella")
                .description("Pizza Gourmet")
                .build();

        // When
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put("/categories/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryBody));

        ResultActions result = mockMvc.perform(request);

        // Then
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.state.value").value(State.ENABLED.getValue()))
                .andExpect(jsonPath("$.state.name").value(State.ENABLED.getName()))
                .andExpect(jsonPath("$.state.enabled").value(State.ENABLED.isEnabled()));
    }

    @Test
    void canDisableCategory() throws Exception {
        // Given
        when(categoryRepository.findById(anyLong()))
                .thenReturn(Optional.of(category));

        when(categoryRepository.save(any(Category.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // When
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete("/categories/" + id);

        ResultActions result = mockMvc.perform(request);

        // Then
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.state.value").value(State.DISABLED.getValue()))
                .andExpect(jsonPath("$.state.name").value(State.DISABLED.getName()))
                .andExpect(jsonPath("$.state.enabled").value(State.DISABLED.isEnabled()));
    }

    @Test
    void canPaginatedSearch() throws Exception {
        // Given
        int page = 1;
        int size = 10;

        Category category2 = Category.builder()
                .id(2L)
                .name("Burger")
                .description("Burger description")
                .urlKey("burger")
                .state(State.ENABLED.getValue())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        List<Category> categories = List.of(category, category2);
        Page<Category> categoryPage = new PageImpl<>(categories, PageRequest.of((page - 1), size), categories.size());

        when(categoryRepository
                .paginatedSearch(
                        anyString(),
                        anyString(),
                        anyString(),
                        anyString(),
                        anyString(),
                        any(Pageable.class)
                )
        ).thenReturn(categoryPage);

        // When
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/categories/paginated-search")
                .param("page", String.valueOf(page))
                .param("size", String.valueOf(size))
                .param("name", "Pizza")
                .param("description", "Pizza")
                .param("state", State.ENABLED.getValue())
                .param("createdAtFrom", "2021-01-01")
                .param("createdAtTo", "2021-12-31")
                .param("sortField", "id")
                .param("sortOrder", "ASC");

        ResultActions result = mockMvc.perform(request);

        // Then
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.content[0].id").value(id))
                .andExpect(jsonPath("$.content[0].name").value(category.getName()))
                .andExpect(jsonPath("$.content[0].description").value(category.getDescription()))
                .andExpect(jsonPath("$.content[0].urlKey").value(category.getUrlKey()))
                .andExpect(jsonPath("$.content[0].state.value").value(State.ENABLED.getValue()))
                .andExpect(jsonPath("$.content[0].state.name").value(State.ENABLED.getName()))
                .andExpect(jsonPath("$.content[0].state.enabled").value(State.ENABLED.isEnabled()))
                .andExpect(jsonPath("$.content[0].createdAt").exists())
                .andExpect(jsonPath("$.content.length()").value(categories.size()))
                .andExpect(jsonPath("$.totalElements").value(categories.size()))
                .andExpect(jsonPath("$.number").value(page))
                .andExpect(jsonPath("$.size").value(size));

    }

    @Test
    void canPaginatedSearchWithOnlyRequiredParams() throws Exception {
        // Given
        int page = 1;
        int size = 10;

        Category category2 = Category.builder()
                .id(2L)
                .name("Burger")
                .description("Burger description")
                .urlKey("burger")
                .state(State.ENABLED.getValue())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        List<Category> categories = List.of(category, category2);
        Page<Category> categoryPage = new PageImpl<>(categories, PageRequest.of((page - 1), size), categories.size());

        when(categoryRepository
                .paginatedSearch(
                        eq(null),
                        eq(null),
                        eq(null),
                        eq(null),
                        eq(null),
                        any(Pageable.class)
                )
        ).thenReturn(categoryPage);

        // When
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/categories/paginated-search")
                .param("page", String.valueOf(page))
                .param("size", String.valueOf(size));

        ResultActions result = mockMvc.perform(request);

        // Then
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.content[0].id").value(id))
                .andExpect(jsonPath("$.content[0].name").value(category.getName()))
                .andExpect(jsonPath("$.content[0].description").value(category.getDescription()))
                .andExpect(jsonPath("$.content[0].urlKey").value(category.getUrlKey()))
                .andExpect(jsonPath("$.content[0].state.value").value(State.ENABLED.getValue()))
                .andExpect(jsonPath("$.content[0].state.name").value(State.ENABLED.getName()))
                .andExpect(jsonPath("$.content[0].state.enabled").value(State.ENABLED.isEnabled()))
                .andExpect(jsonPath("$.content[0].createdAt").exists())
                .andExpect(jsonPath("$.content.length()").value(categories.size()))
                .andExpect(jsonPath("$.totalElements").value(categories.size()))
                .andExpect(jsonPath("$.number").value(page))
                .andExpect(jsonPath("$.size").value(size));
    }
}