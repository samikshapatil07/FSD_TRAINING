
package com.spring.model;

import org.springframework.stereotype.Component;

@Component
public class PolicyHolder {
	
	private int id;
	private String name;
	private String panNo; 
	
	private Address address;

	public PolicyHolder(int id, String name, String panNo, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.panNo = panNo;
		this.address = address;
	}

	public PolicyHolder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "PolicyHolder [id=" + id + ", name=" + name + ", panNo=" + panNo + ", address=" + address + "]";
	} 
	
	
}
