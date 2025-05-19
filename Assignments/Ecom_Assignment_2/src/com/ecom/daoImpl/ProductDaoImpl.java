package com.ecom.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ecom.dao.ProductDao;
import com.ecom.exception.ProductNotFoundException;
import com.ecom.model.Category;
import com.ecom.model.Product;
import com.ecom.utility.DBUtility;

public class ProductDaoImpl implements ProductDao {
	
	DBUtility db =  DBUtility.getInstance();

	@Override
	public void addProduct(Product product,int categoryId) {
		Connection conn = db.connect();
		String sql = "INSERT INTO product (title, price, description, category_id) VALUES (?, ?, ?, ?)";
        try{
        	PreparedStatement ps = conn.prepareStatement(sql);
        	ps.setString(1, product.getTitle());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.setInt(4, categoryId);
            ps.executeUpdate();
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }finally {
        	db.close();
        }
		
	}

	@Override
	public List<Product> getProductsByCategory(int categoryId) {
		Connection conn = db.connect();
		List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE category_id = ?";
        try{
        	PreparedStatement ps = conn.prepareStatement(sql);
        	ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setTitle(rs.getString("title"));
                p.setPrice(rs.getDouble("price"));
                p.setDescription(rs.getString("description"));
                Category category = new Category();
                category.setId(rs.getInt("category_id"));
                p.setCategory(category);
                products.add(p);
            }
            
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }finally {
        	db.close();
        }
        
		return products;
	}

	@Override
	public List<Product> getAllProducts() {
		Connection conn = db.connect();
		List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try{
        	PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
            	Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setTitle(rs.getString("title"));
                p.setPrice(rs.getDouble("price"));
                p.setDescription(rs.getString("description"));
                
                Category category = new Category();
                category.setId(rs.getInt("category_id"));
                p.setCategory(category);
                
                products.add(p);
            }
            
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }finally {
        	db.close();
        }
        
		return products;
	}

	@Override
	public Product getById(int id) throws ProductNotFoundException {
		Connection conn = db.connect();
		Product product = null;
		String sql = "SELECT * FROM product WHERE id = ?";
        try{
        	PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rst=  pstmt.executeQuery();
			if(rst.next() == true) {
				product = new Product();
				product.setId(rst.getInt("id"));
		        product.setTitle(rst.getString("title"));
		        product.setPrice(rst.getDouble("price"));
		        product.setDescription(rst.getString("description"));
		        Category category = new Category();
		        category.setId(rst.getInt("category_id"));
		        product.setCategory(category);	
			}else {
	            throw new ProductNotFoundException("Product ID " + id + " not found.");
	        }
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }finally {
        	db.close();
        }
        return product;
	}

	

}
