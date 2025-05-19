package com.ecom.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ecom.dao.CustomerDao;
import com.ecom.exception.CustomerNotFoundException;
import com.ecom.model.Customer;
import com.ecom.utility.DBUtility;

public class CustomerDaoImpl implements CustomerDao {
	
	DBUtility db =  DBUtility.getInstance();

	@Override
	public void addCustomer(Customer customer) {
		Connection conn = db.connect();
		String sql = "INSERT INTO customer (name, city) VALUES (?, ?)";
        try{
        	PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getCity());
            ps.executeUpdate();
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }finally {
        	db.close();
        }

	}

	@Override
	public void delete(int id) throws CustomerNotFoundException {
		Connection conn = db.connect();
		String sql = "DELETE FROM customer WHERE id = ?";
        try{
        	PreparedStatement ps = conn.prepareStatement(sql);
        	ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new CustomerNotFoundException("Customer not found with ID: " + id);
            }
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }finally {
        	db.close();
        }

	}

	@Override
	public List<Customer> getAllCustomers() {
		Connection conn = db.connect();
		List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        try{
        	PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setCity(rs.getString("city"));
                customers.add(customer);
            }
            
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }finally {
        	db.close();
        }
        
		return customers;
	}

	@Override
	public Customer getById(int id) throws CustomerNotFoundException {
		Connection conn = db.connect();
		Customer customer = null;
		String sql = "SELECT * FROM customer WHERE id = ?";
        try{
        	PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rst=  pstmt.executeQuery();
			if(rst.next() == true) {
				customer = new Customer();
				customer.setId(rst.getInt("id"));
				customer.setName(rst.getString("name"));
			    customer.setCity(rst.getString("city"));	
			}else {
	            throw new CustomerNotFoundException("Customer ID " + id + " not found.");
	        }
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }finally {
        	db.close();
        }
        //throw new CategoryNotFoundException(" ");
        return customer; 
	}

}
