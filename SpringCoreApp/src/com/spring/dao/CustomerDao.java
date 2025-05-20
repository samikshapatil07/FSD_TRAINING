package com.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.model.Customer;
@Repository
public class CustomerDao {

	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public CustomerDao(JdbcTemplate jdbcTemplate) { 
		this.jdbcTemplate = jdbcTemplate;
	}

	public void createCustomerTable(){
		String sql="create table if not exists customer(id int primary key auto_increment, name varchar(255), city varchar(255))";
		jdbcTemplate.execute(sql);
	}

	public Customer getCustomer() {
	    return new Customer(1,"john doe","mumbai");
	}

	public void insertCustomer(String name, String city) {
		String sql="insert into customer(name,city) values (?,?)";
		jdbcTemplate.update(sql,name,city);
	}

	
}