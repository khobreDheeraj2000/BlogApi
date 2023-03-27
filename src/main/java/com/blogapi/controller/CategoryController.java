package com.blogapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapi.dto.CategoryDto;
import com.blogapi.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService ;
	
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		List<CategoryDto> categoriesDto = categoryService.getAllCategory();
		return new ResponseEntity<>(categoriesDto , HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public CategoryDto getCategory(@PathVariable ("id") int id ){
		 CategoryDto categoryDto = categoryService.getCategory(id);
		 return categoryDto ;
	}
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto categoryDto1 = categoryService.createCategory(categoryDto);
		return new ResponseEntity<>(categoryDto1, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCategory(@PathVariable int id) {
		categoryService.deleteCategory(id);
	}
	
	@PutMapping("/{id}")
	public CategoryDto updateCategory(@Valid @RequestBody CategoryDto categoryDto ,@PathVariable int id) {
		return categoryService.updateCategory(categoryDto, id);
		
	}
	
}
