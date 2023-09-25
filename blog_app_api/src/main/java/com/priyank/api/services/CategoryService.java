package com.priyank.api.services;

import java.util.List;

import com.priyank.api.entites.Category;
import com.priyank.api.payload.CategoryDto;

public interface CategoryService {
        CategoryDto createCategory(CategoryDto categoryDto);
        CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
        void deleteCategory(Integer categoryId);
        CategoryDto getCategory(Integer categoryId);
        List<CategoryDto> getCategories();
}
