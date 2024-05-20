package com.springboot.blog.service.impl;

import com.springboot.blog.dto.CategoryDto;
import com.springboot.blog.dto.PostDto;
import com.springboot.blog.entity.Category;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.service.CategoryService;
import mapper.CategoryMapper;
import mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper mapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {

        Category category = mapToEntity(categoryDto);

        Category newCategory = categoryRepository.save(category);

        return mapToDto(newCategory);
    }

    @Override
    public CategoryDto getCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        return mapToDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        Category categoryUpdated = categoryRepository.save(category);

        return mapToDto(categoryUpdated);
    }

    @Override
    public void deleteCategory(Long categoryId) {
//        verifico primero si se encuentra la categoria
        getCategory(categoryId);

//        si la categoria existe procedo a borrarla. Tambien podria acceder a la categoria encontrada y borrarla con el metodo delete(category) en lugar de hacerlo por medio de su id
        categoryRepository.deleteById(categoryId);
    }


    // convert Entity into Dto
    private CategoryDto mapToDto(Category category) {
        CategoryDto categoryDto = CategoryMapper.MAPPER.mapToDto(category);
        return categoryDto;
    }

    private Category mapToEntity(CategoryDto categoryDto) {
        Category category = CategoryMapper.MAPPER.mapToEntity(categoryDto);
        return category;
    }

}
