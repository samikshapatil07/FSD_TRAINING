package com.springboot.lms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


	@Entity
	@Table(name = "author")
	public class Author { //a

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	     
	    private String name;
	    private String contact;
	    private String website;
	    private String profilePic;
	    
	    private boolean isActive;
	    
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

		public String getWebsite() {
			return website;
		}

		public void setWebsite(String website) {
			this.website = website;
		}

		public String getProfilePic() {
			return profilePic;
		}

		public void setProfilePic(String profilePic) {
			this.profilePic = profilePic;
		}

		public boolean isActive() {
			return isActive;
		}

		public void setActive(boolean isActive) {
			this.isActive = isActive;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		

}
