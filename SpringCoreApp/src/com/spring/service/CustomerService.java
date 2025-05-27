package com.spring.service;

import java.awt.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.CustomerDao;
import com.spring.model.Customer;

@Service //<-- This registers this class CustomerService with Spring context
public class CustomerService {
	@Autowired   
	CustomerDao customerDao;
	
	
	public CustomerService(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public Customer getCustomer() {
		 
		return customerDao.getCustomer();
	}

	public void createTable() {
		customerDao.createCustomerTable();
		
	}

	public void insertCustomer(String name, String city) {
		customerDao.insertCustomer(name,city);
		
	}
    public List<Customer> getAll() {
		
		return customerDao.getAll();
	}
    
    
    public List<Customer> getAllv2() {
		List<Map<String, Object>> list =  customerDao.getAllv2();
		List<Customer> listCustomer = new ArrayList<>();
	//	System.out.println(list);
		for(Map<String,Object> map : list) {
			//System.out.println(map);
			Customer c = new Customer();
			c.setId((int)map.get("id"));
			c.setName((String)map.get("name"));
			c.setCity((String)map.get("city"));
			listCustomer.add(c);
		}
		return listCustomer; 
	}

}
	

}