package com.jobportal.JobPortal.dto;

public class HrDTO {
	private int id;
	private String name;
	private String companyName;
	private String username;
	
	
	public HrDTO() {

	}

	public HrDTO(int id, String name, String companyName, String username) {
		super();
		this.id = id;
		this.name = name;
		this.companyName = companyName;
		this.username = username;
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


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	
}
