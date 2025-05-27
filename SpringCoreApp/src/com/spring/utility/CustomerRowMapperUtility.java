package com.spring.utility;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.model.Customer;

public class CustomerRowMapperUtility implements RowMapper<Customer>{
	
	@Override
	public Customer mapRow(ResultSet rst, int rowNum) throws SQLException {
		return new Customer(
				rst.getInt("id"), 
				rst.getString("name"), 
				rst.getString("city")); 
	}
}