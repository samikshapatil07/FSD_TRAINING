package com.ecom.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ecom.dao.CategoryDao;
import com.ecom.exception.CategoryNotFoundException;
import com.ecom.model.Category;
import com.ecom.utility.DBUtility;

public class CategoryDaoImpl implements CategoryDao {
	
	DBUtility db =  DBUtility.getInstance();

	@Override
	public void addCategory(Category category) {
		Connection conn = db.connect();
		String sql = "INSERT INTO category(name) VALUES (?)";
        try{
        	PreparedStatement ps = conn.prepareStatement(sql);
        	ps.setString(1, category.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }finally {
        	db.close();
        }

	}

	@Override
	public Category getById(int id) throws CategoryNotFoundException {
		Connection conn = db.connect();
		Category category = null;
		String sql = "SELECT * FROM category WHERE id = ?";
        try{
        	PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rst=  pstmt.executeQuery();
			if(rst.next() == true) {
				category = new Category();
				category.setId(rst.getInt("id"));
				category.setName(rst.getString("name"));	
			}
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }finally {
        	db.close();
        }
        //throw new CategoryNotFoundException(" ");
        return category; 
	}

	@Override
	public List<Category> viewAllCategories() {
		Connection conn = db.connect();
		List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM category";
        try{
        	Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
            
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }finally {
        	db.close();
        }
        
		return categories;
	}

}
