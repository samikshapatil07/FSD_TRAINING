package com.ecom.service;

import java.util.List;

import com.ecom.dao.CategoryDao;
import com.ecom.daoImpl.CategoryDaoImpl;
import com.ecom.exception.CategoryNotFoundException;
import com.ecom.model.Category;

public class CategoryService {
	
	private CategoryDao categoryDao = new CategoryDaoImpl();

    public void addCategory(Category category) {
        categoryDao.addCategory(category);
    }

    public List<Category> viewAllCategories() {
        return categoryDao.viewAllCategories();
    }

    public Category getById(int categoryId) throws CategoryNotFoundException {
        Category category = categoryDao.getById(categoryId);
        if (category == null) {
            throw new CategoryNotFoundException("Category with ID " + categoryId + " not found.");
        }
        return category;
    }

}
