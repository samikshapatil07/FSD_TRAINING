package com.ecom.dao;

import java.util.List;

import com.ecom.exception.CategoryNotFoundException;
import com.ecom.model.Category;

public interface CategoryDao {
	
	void addCategory(Category category);
	Category getById(int id) throws CategoryNotFoundException;
    List<Category> viewAllCategories();

}
