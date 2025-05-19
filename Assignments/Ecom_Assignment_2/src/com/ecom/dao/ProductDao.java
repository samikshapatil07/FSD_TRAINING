package com.ecom.dao;

import java.util.List;

import com.ecom.exception.ProductNotFoundException;
import com.ecom.model.Product;

public interface ProductDao {
	
	void addProduct(Product product,int categoryId);
	List<Product> getProductsByCategory(int categoryId);
	Product getById(int id) throws ProductNotFoundException;
	List<Product> getAllProducts();

}
