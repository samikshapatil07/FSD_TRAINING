package com.springboot.lms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "learner")
public class Learner { //l 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
     
    private String name;
    private String contact;
    
    @OneToOne
    private User user; 
    
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    
}