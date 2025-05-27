package com.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.model.Address;
import com.spring.model.PolicyHolder;

@Repository
public class PolicyHolderDao {

	
	private JdbcTemplate jdbcTemplate; 
	
	@Autowired
	public PolicyHolderDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void insert(PolicyHolder policyHolder) {
		String sql="insert into policyholder(name, panNo,address_id) values (?,?,?)";
		jdbcTemplate.update(sql, 
							policyHolder.getName(),
							policyHolder.getPanNo(),
							policyHolder.getAddress().getId() ) ; 
	}

	public void insertAddress(Address address) {
		String sql="insert into address(id,street,city,state) values (?,?,?,?)";
		jdbcTemplate.update(sql, 
							address.getId(),
							address.getStreet(),
							address.getCity(), 
							address.getState())	;
		
		
	}

	public List<PolicyHolder> getAllWithAddres() {
		 String sql = "select * from policyholder ph JOIN address a ON ph.address_id = a.id";
		return jdbcTemplate.query(sql, new RowMapper<PolicyHolder>() {
			@Override
			public PolicyHolder mapRow(ResultSet rst, int rowNum) throws SQLException {
				 Address address = new Address();
				 address.setStreet(rst.getString("street"));
				 address.setCity(rst.getString("city"));
				 address.setState(rst.getString("state"));
				 
				 PolicyHolder ph = new PolicyHolder();
				 ph.setName(rst.getString("name"));
				 ph.setPanNo(rst.getString("panNo"));
				 ph.setAddress(address);
				 
				return ph;
			}
		});
	}

}