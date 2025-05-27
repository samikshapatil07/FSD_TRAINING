package com.hibernate.main.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity   //<--this annotation tells hibernate to create a table for this class in DB
@Table(name = "learner") //<-- this will create the table with name learner 
public class Learner {

	@Id //<-- this makes id a primary key 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable = false) //<-- this will add NOT NULL constraint to name 
	private String name; 
	private String email;
	private String contact;
	
	/*@ManyToMany
	private List<Course> course;
	*/
	
	
	public Learner(int id, String name, String email, String contact) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.contact = contact;
	}
	public Learner() {
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	@Override
	public String toString() {
		return "Learner [id=" + id + ", name=" + name + ", email=" + email + ", contact=" + contact + "]";
	} 
	
	
}