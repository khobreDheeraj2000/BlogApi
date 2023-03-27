package com.blogapi.services;

import java.util.List;

import com.blogapi.dto.CategoryDto;

public interface CategoryService {
	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(CategoryDto categoryDto , int id);
	List<CategoryDto> getAllCategory();
	CategoryDto getCategory(int id);
    void deleteCategory(int id);
}
