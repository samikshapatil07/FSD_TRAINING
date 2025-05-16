
package com.lms.model;

public class Learner { //encapsulate the model class 
	private int id; 
	private String name;
	private String email;
	
	/* create constructors */
	
	public Learner() {  }  //Allows creating blank Object

	public Learner(int id, String name, String email) { //Allows initializing fields during object creation
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	/* Getters and Setters */
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	/*toString() - i will get info about instance variable when I display the object*/
	@Override
	public String toString() {
		return "Learner [id=" + id + ", name=" + name + ", email=" + email + "]";
	}	
}
