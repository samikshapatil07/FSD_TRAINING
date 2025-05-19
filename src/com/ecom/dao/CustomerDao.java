package com.ecom.dao;

import java.util.List;

import com.ecom.exception.CustomerNotFoundException;
import com.ecom.model.Customer;

public interface CustomerDao {
	
	void addCustomer(Customer customer);
	void delete(int id) throws CustomerNotFoundException;
	Customer getById(int id) throws CustomerNotFoundException;
    List<Customer> getAllCustomers();

}
