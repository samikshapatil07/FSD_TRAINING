package com.jobportal.JobPortal.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "hr")
public class Hr {  //h

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hr_id")
    private Long id; // primary key, auto-increment

    @Column(nullable = false)
    private String name;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    // This will create the foreign key user_id column in hr table
    @OneToOne(cascade = CascadeType.ALL)  // Ensures User gets saved with HR  
    @JoinColumn(name = "user_id")
    private User user;
 
    @OneToMany(mappedBy = "hr", cascade = CascadeType.ALL)
    private List<JobPosting> jobPostings;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<JobPosting> getJobPostings() {
		return jobPostings;
	}

	public void setJobPostings(List<JobPosting> jobPostings) {
		this.jobPostings = jobPostings;
	}

}
