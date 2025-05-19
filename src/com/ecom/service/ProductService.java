package com.ecom.service;

import java.util.List;

import com.ecom.dao.ProductDao;
import com.ecom.daoImpl.ProductDaoImpl;
import com.ecom.model.Product;

public class ProductService {
	
	private ProductDao productDao = new ProductDaoImpl();

    public void addProduct(Product product,int categoryId) {
        productDao.addProduct(product,categoryId);
    }

    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public List<Product> getProductsByCategory(int categoryId) {
        return productDao.getProductsByCategory(categoryId);
    }
    
}
