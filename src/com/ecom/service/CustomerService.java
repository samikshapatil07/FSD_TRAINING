package com.ecom.service;

import java.util.List;

import com.ecom.dao.CustomerDao;
import com.ecom.daoImpl.CustomerDaoImpl;
import com.ecom.exception.CustomerNotFoundException;
import com.ecom.exception.InvalidInputException;
import com.ecom.model.Customer;

public class CustomerService {
	
	CustomerDao dao = new CustomerDaoImpl(); // Polymorphic reference

    public List<Customer> getAllCustomers() {
        return dao.getAllCustomers();
    }

    public void addCustomer(Customer customer) throws InvalidInputException {
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            throw new InvalidInputException("Invalid customer name provided.");
        }
        if (customer.getCity() == null || customer.getCity().trim().isEmpty()) {
            throw new InvalidInputException("Invalid customer city provided.");
        }
        dao.addCustomer(customer);
    }

    public void deleteCustomer(int id) throws CustomerNotFoundException {
        dao.delete(id);
    }

}
