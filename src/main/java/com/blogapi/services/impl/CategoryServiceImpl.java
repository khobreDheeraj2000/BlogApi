package com.blogapi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapi.dto.CategoryDto;
import com.blogapi.entity.Category;
import com.blogapi.repository.CategoryRepository;
import com.blogapi.services.CategoryService;
import org.modelmapper.ModelMapper;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired 
	ModelMapper modelMapper ;
	
	@Autowired
	CategoryRepository categoryRepository ;
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto,Category.class);
		Category savedCategory = categoryRepository.save(category);
		return this.modelMapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto , int id) {
		Category category = categoryRepository.findById(id).orElseThrow();
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDiscription(categoryDto.getCategoryDiscription());
		Category savedCategory = categoryRepository.save(category);
		return this.modelMapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = categoryRepository.findAll();
		List<CategoryDto> categoryDtoList = categories.stream().map(category-> this.modelMapper.map(category,CategoryDto.class)).toList();
		return categoryDtoList;
	}

	@Override
	public CategoryDto getCategory(int id) {
		Category category = categoryRepository.findById(id).orElseThrow();
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public void deleteCategory(int id) {
		Category category = categoryRepository.findById(id).orElseThrow();
		categoryRepository.delete(category);	
	}

}
