package com.ijse.database.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.database.entity.Category;

@Service
public interface CategoryService {
    List<Category> getAllCategories();
    Category findCategoryById(Long id);
    Category createCategory(Category category);
    Category updateCategory(Long id, Category category);
}
